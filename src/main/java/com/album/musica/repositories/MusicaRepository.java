package com.album.musica.repositories;

import com.album.musica.entities.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {
    @Override
    Musica save(Musica entity);
}
