package com.album.musica.dto;

import com.album.musica.entities.Artista;
import com.album.musica.entities.Musica;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlbumDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String nome;
    private Integer ano;
    private List<MusicaDTO> musicas;
    private List<ArtistaDTO> participantes;
    private Long duracao;
}
