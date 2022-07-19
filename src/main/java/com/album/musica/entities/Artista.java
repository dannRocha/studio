package com.album.musica.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "artista")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long ID;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nacionalidade")
    private String nacionalidade;

    @ManyToMany
    @JoinTable(
            name = "artista_musica",
            joinColumns = @JoinColumn(name = "artista_id"),
            inverseJoinColumns = @JoinColumn(name = "musica_id")
    )
    private List<Musica> musicasComoAutor;

    @ManyToMany
    @JoinTable(
            name = "artista_album",
            joinColumns = @JoinColumn(name = "artista_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id")
    )
    private List<Album> albums;

    public static Artista from(String nome, String nacionalidade) {
        return Artista.builder()
                .nome(nome)
                .nacionalidade(nacionalidade)
                .build();
    }
}
