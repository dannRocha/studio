package com.album.musica.mapper;


import com.album.musica.dto.ArtistaCadastroDTO;
import com.album.musica.dto.ArtistaDTO;
import com.album.musica.entities.Artista;

import java.util.Objects;
import java.util.stream.Collectors;

public class ArtistaMapper {
    public static Artista DTOCadastroToEntity(ArtistaCadastroDTO artista) {
        return Artista.builder()
                .nome(artista.getNome())
                .nacionalidade(artista.getNacionalidade())
                .build();
    }

    public static Artista DTOToEntity(ArtistaDTO artista) {
        return Artista.builder()
                .ID(artista.getID())
                .nome(artista.getNome())
                .nacionalidade(artista.getNacionalidade())
                .musicasComoAutor(Objects.isNull(artista.getMusicasComoAutor()) ? null : artista.getMusicasComoAutor().stream().map(MusicaMapper::SimpleDTOToEntity).collect(Collectors.toList()))
                .build();
    }

    public static ArtistaDTO entityArtistaDTO(Artista artista) {
        return ArtistaDTO.builder()
                .ID(artista.getID())
                .nome(artista.getNome())
                .nacionalidade(artista.getNacionalidade())
                .albums(Objects.isNull(artista.getAlbums()) ? null : artista.getAlbums().stream().map(AlbumMapper::entityToSimpleDTO).collect(Collectors.toList()))
                .musicasComoAutor(Objects.isNull(artista.getMusicasComoAutor())? null : artista.getMusicasComoAutor().stream().map(MusicaMapper::entityToSimpleDTO).collect(Collectors.toList()))
                .build();
    }
}
