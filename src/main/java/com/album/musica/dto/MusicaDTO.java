package com.album.musica.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class MusicaDTO {
    private Long ID;
    private String nome;
    private Integer duracao;
    private List<ArtistaDTO> interpretes;
}
