package com.album.musica.mapper;

import com.album.musica.dto.MusicaCadastroDTO;
import com.album.musica.dto.MusicaDTO;
import com.album.musica.dto.MusicaSimpleDTO;
import com.album.musica.entities.Musica;

import java.util.Objects;
import java.util.stream.Collectors;

public class MusicaMapper {
    public static MusicaDTO entityToDTO(Musica musica) {
        return MusicaDTO.builder()
                .ID(musica.getID())
                .nome(musica.getNome())
                .duracao(musica.getDuracao())
                .interpretes(Objects.isNull(musica.getInterpretes()) ? null : musica.getInterpretes().stream().map(ArtistaMapper::entityArtistaDTO).collect(Collectors.toList()))
                .build();
    }
    public static MusicaSimpleDTO entityToSimpleDTO(Musica musica) {
        return MusicaSimpleDTO.builder()
                .ID(musica.getID())
                .nome(musica.getNome())
                .build();
    }

    public static Musica DTOToEntity(MusicaDTO musica) {
        return Musica.builder()
                .ID(musica.getID())
                .nome(musica.getNome())
                .duracao(musica.getDuracao())
                .build();
    }

    public static Musica SimpleDTOToEntity(MusicaSimpleDTO musica) {
        return Musica.builder()
                .ID(musica.getID())
                .nome(musica.getNome())
                .build();
    }

    public static Musica DTOCadastroToEntity(MusicaCadastroDTO musica) {
        return Musica.builder()
                .nome(musica.getNome())
                .duracao(musica.getDuracao())
                .albums(Objects.isNull(musica.getAlbums()) ? null : musica.getAlbums().stream().map(AlbumMapper::DTOToEntity).collect(Collectors.toList()))
                .autores(Objects.isNull(musica.getAutores()) ? null : musica.getAutores().stream().map(ArtistaMapper::DTOToEntity).collect(Collectors.toList()))
                .interpretes(Objects.isNull(musica.getInterpretes()) ? null : musica.getInterpretes().stream().map(ArtistaMapper::DTOToEntity).collect(Collectors.toList()))
                .build();
    }
}
