package com.album.musica.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "musica")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long ID;

    @Column(name = "nome")
    private String nome;

    @Column(name = "duracao")
    private Integer duracao;

    @ManyToMany
    @JoinTable(
            name = "album_musica",
            joinColumns = @JoinColumn(name = "musica_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id")
    )
    private List<Album> albums;

    @ManyToMany
    @JoinTable(
            name = "musica_interprete",
            joinColumns = @JoinColumn(name = "musica_id"),
            inverseJoinColumns = @JoinColumn(name = "interprete_id")
    )
    private List<Artista> interpretes;

    @ManyToMany
    @JoinTable(
            name = "artista_musica",
            joinColumns = @JoinColumn(name = "musica_id"),
            inverseJoinColumns = @JoinColumn(name = "artista_id")
    )
    private List<Artista> autores;

//    @PrePersist
//    public void calcularDuracaoAlbum() {
//        for(var album : albums) {
//            album.setDuracao(album.getDuracao() + duracao);
//        }
//    }
}
