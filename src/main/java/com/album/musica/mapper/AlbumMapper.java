package com.album.musica.mapper;

import com.album.musica.dto.AlbumCadastroDTO;
import com.album.musica.dto.AlbumDTO;
import com.album.musica.dto.AlbumSimpleDTO;
import com.album.musica.entities.Album;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AlbumMapper {
    public static AlbumDTO entityToDTO(Album album) {
        return AlbumDTO.builder()
                .ID(album.getID())
                .nome(album.getNome())
                .ano(album.getAno())
                .musicas(Objects.isNull(album.getMusicas()) ? List.of() : album.getMusicas().stream().map(MusicaMapper::entityToDTO).collect(Collectors.toList()) )
                .participantes(Objects.isNull(album.getParticipantes())? List.of() : album.getParticipantes().stream().map(ArtistaMapper::entityArtistaDTO).collect(Collectors.toList()))
                .duracao(album.getDuracao()  == 0 && Objects.nonNull(album.getMusicas()) ? album.getMusicas().stream().map(m -> m.getDuracao()).reduce(0, (acc, value) -> acc + value).longValue() : 0 )
                .build();
    }

    public static AlbumSimpleDTO entityToSimpleDTO(Album album) {
        return AlbumSimpleDTO.builder()
                .ID(album.getID())
                .nome(album.getNome())
                .ano(album.getAno())
                .duracao(album.getDuracao())
                .build();
    }

    public static Album DTOToEntity(AlbumDTO album) {
        return Album.builder()
                .ID(album.getID())
                .nome(album.getNome())
                .ano(album.getAno())
                .build();
    }
    public static Album DTOCadastroToEntity(AlbumCadastroDTO album) {
        return Album.builder()
                .nome(album.getNome())
                .ano(album.getAno())
                .participantes(Objects.isNull(album.getParticipates()) ? Set.of() : album.getParticipates().stream().map(ArtistaMapper::DTOToEntity).collect(Collectors.toSet()))
                .build();
    }
}
