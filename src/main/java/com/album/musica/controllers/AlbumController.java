package com.album.musica.controllers;

import com.album.musica.dto.AlbumCadastroDTO;
import com.album.musica.dto.AlbumDTO;
import com.album.musica.entities.Album;
import com.album.musica.services.AlbumService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/album")
@AllArgsConstructor
public class AlbumController {

    private final AlbumService service;

    @PostMapping
    public ResponseEntity<?> salvarAlbum(@Valid @RequestBody AlbumCadastroDTO album) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.salvarAlbum(album));
    }

    @GetMapping("{id}")
    public ResponseEntity<AlbumDTO> buscarAlbum(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarAlbumPorId(id));
    }

    @GetMapping
    public ResponseEntity<?> buscarTodosAlbums(
            @RequestParam(required = false) String sort,
            @PageableDefault(sort = "nome", page = 0, size = 10, direction = Sort.Direction.ASC) Pageable paginacao
    ) {
        return ResponseEntity.ok(service.buscarTodosAlbuns(paginacao, sort));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizarAlbum(@PathVariable Long id, @Valid @RequestBody AlbumCadastroDTO album) {
        return ResponseEntity
                .ok(service.atualizarAlbum(id, album));
    }
}

