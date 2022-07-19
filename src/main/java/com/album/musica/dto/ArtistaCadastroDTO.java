package com.album.musica.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistaCadastroDTO {

    @NotEmpty
    @Size(max = 254)
    private String nome;

    @NotEmpty
    @Size(max = 254)
    private String nacionalidade;
}
