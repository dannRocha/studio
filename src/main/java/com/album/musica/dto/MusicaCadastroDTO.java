package com.album.musica.dto;

import com.album.musica.entities.Artista;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MusicaCadastroDTO {

    @NotEmpty
    private String nome;

    @NotNull
    private Integer duracao;

    @NotEmpty
    private List<ArtistaDTO> autores;

    @NotEmpty
    private List<ArtistaDTO> interpretes;

    @NotEmpty
    private List<AlbumDTO> albums;

}
