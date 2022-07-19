package com.album.musica.dto;

import com.album.musica.entities.Album;
import com.album.musica.entities.Musica;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ArtistaDTO {
    private Long ID;
    private String nome;
    private String nacionalidade;
    private List<MusicaSimpleDTO> musicasComoAutor;
    private List<AlbumSimpleDTO> albums;
}
