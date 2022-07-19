package com.album.musica.repositories;

import com.album.musica.entities.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component

public class CustomAlbumRepositoryImpl implements CustomAlbumRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Album> findAllAndOrder(Pageable pageable) {

        String orderBy = pageable.getSort().toString().replaceAll(":", " ");
        var query = entityManager.createNativeQuery(
                "select " +
                   " album.id id, " +
                   " album.nome nome, " +
                   " album.ano ano, " +
                   " sum(musica.duracao) as duracao " +
                   "from album, musica, album_musica " +
                   "where album_musica.album_id = album.id " +
                   " and album_musica.musica_id = musica.id " +
                   "group by album.id " +
                   "order by " + orderBy
                )
                .setFirstResult(pageable.getPageNumber())
                .setMaxResults(pageable.getPageSize());


        List<Object[]> objects = query.getResultList();
        var albums = objetListToAlbumList(objects);

        for (var album : albums) {
            var albumSalvo = entityManager.find(Album.class, album.getID());

            if(Objects.isNull(albumSalvo)) {
                continue;
            }

            album.setMusicas(albumSalvo.getMusicas());
            album.setParticipantes(albumSalvo.getParticipantes());
        }


        return new PageImpl<Album>(albums, pageable, albums.size());
    }

    List<Album> objetListToAlbumList(List<Object[]> rawResult) {
        var albums = new ArrayList<Album>();
        for (int i = 0; i < rawResult.size(); i++) {
            var album = Album.builder()
                    .ID(Long.parseLong(rawResult.get(i)[0].toString()))
                    .nome(rawResult.get(i)[1].toString())
                    .ano(Integer.parseInt(rawResult.get(i)[2].toString()))
                    .duracao(Long.parseLong(rawResult.get(i)[3].toString()))
                    .build();

            albums.add(album);
        }

        return albums;
    }
}
