package com.album.musica.controllers;

import com.album.musica.dto.MusicaCadastroDTO;
import com.album.musica.services.MusicaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/musica")
@AllArgsConstructor
public class MusicaController {

    private final MusicaService service;

    @PostMapping
    public ResponseEntity<?> salvarMusica(@Valid @RequestBody MusicaCadastroDTO musica) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.salvarMusica(musica));
    }

    @GetMapping
    public ResponseEntity<?> buscarTodasMusicas(
            @PageableDefault(sort = "nome", page = 0, size = 10, direction = Sort.Direction.DESC) Pageable paginacao) {
        return ResponseEntity
                .ok(service.buscarTodasMusicas(paginacao));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarTodasMusicas(@PathVariable Long id) {
        return ResponseEntity
                .ok(service.buscarMusicaPorId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> salvarMusica(@PathVariable Long id, @Valid @RequestBody MusicaCadastroDTO musica) {
        return ResponseEntity
                .ok(service.atualizarMusica(id, musica));
    }
}
