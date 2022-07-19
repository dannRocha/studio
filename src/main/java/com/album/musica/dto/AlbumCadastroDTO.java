package com.album.musica.dto;

import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumCadastroDTO {

    @NotEmpty
    private String nome;

    @NotNull
    private Integer ano;

    @NotEmpty
    private List<ArtistaDTO> participates;
}
