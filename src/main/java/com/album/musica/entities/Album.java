package com.album.musica.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "album")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long ID;

    @Column(name = "nome")
    private String nome;

    @Column(name = "ano")
    private Integer ano;


    @ManyToMany
    @JoinTable(
            name = "album_musica",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "musica_id")
    )
    private List<Musica> musicas;

    @ManyToMany
    @JoinTable(
            name = "artista_album",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artista_id")
    )
    private Set<Artista> participantes;

    @Column(name = "duracao")
    private Long duracao;

    @PrePersist
    public void definirDuracao() {
        duracao = 0L;
    }

}
