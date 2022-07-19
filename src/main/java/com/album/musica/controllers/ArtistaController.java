package com.album.musica.controllers;

import com.album.musica.dto.ArtistaCadastroDTO;
import com.album.musica.services.AlbumService;
import com.album.musica.services.ArtistaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/artista")
@AllArgsConstructor
public class ArtistaController {

    private final ArtistaService service;
    private final AlbumService albumService;

    @PostMapping
    public ResponseEntity<?> registarArtista(@Valid @RequestBody ArtistaCadastroDTO artista) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.salvarArtista(artista));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarArtistarPorID(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarArtistaPorId(id));
    }

    @GetMapping("{id}/album")
    public ResponseEntity<?> buscarArtistarPorID(
            @PathVariable Long id,
            @PageableDefault(sort = "nome", page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity
                .ok(albumService.buscarAlbumPorArtistaId(pageable, id));
    }

    @GetMapping
    public ResponseEntity<?>
        buscarArtistarPorNomeOuNacionalidade(
                @RequestParam(required = false, value = "nome") String nome,
                @RequestParam(required = false, value = "nacionalidade") String nacionalidade,
                @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(
                service.buscarArtistasPor(nome, nacionalidade, pageable)
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizarArtista(@Valid @RequestBody ArtistaCadastroDTO artista, @PathVariable Long id) {
        return ResponseEntity
                .ok(service.atualizarArtista(id, artista));
    }
}
