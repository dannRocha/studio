package com.album.musica.repositories;

import com.album.musica.entities.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomAlbumRepository {
    Page<Album> findAllAndOrder(Pageable pageable);
    Page<Album> findAllAndOrderByArtista(Pageable pageable, Long id);
}
