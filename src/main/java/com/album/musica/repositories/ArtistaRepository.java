package com.album.musica.repositories;


import com.album.musica.entities.Artista;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Page<Artista> findAll(Pageable pageable);
    Page<Artista> findByNomeContainingIgnoreCaseOrNacionalidadeContainingIgnoreCase(Pageable pageable, String nome, String nacionalidade);
}
