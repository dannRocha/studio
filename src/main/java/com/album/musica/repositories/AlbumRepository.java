package com.album.musica.repositories;

import com.album.musica.entities.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long>, CustomAlbumRepository {
    Page<Album> findAll(Pageable pageable);
}
