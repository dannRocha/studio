package com.album.musica.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumSimpleDTO {
    private Long ID;
    private String nome;
    private Integer ano;
    private Long duracao;
}
