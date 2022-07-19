package com.album.musica.services;

import com.album.musica.dto.ArtistaCadastroDTO;
import com.album.musica.dto.ArtistaDTO;
import com.album.musica.entities.Artista;
import com.album.musica.expcetion.EntityNotFoundException;
import com.album.musica.mapper.ArtistaMapper;
import com.album.musica.repositories.ArtistaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ArtistaService {
    private final ArtistaRepository artistaRepository;

    public ArtistaDTO salvarArtista(ArtistaCadastroDTO artista) {
        var artistaEntity = ArtistaMapper.DTOCadastroToEntity(artista);
        var artistaSalvo = artistaRepository.save(artistaEntity);

        if (Objects.isNull(artistaSalvo)) {
            throw new EntityNotFoundException("não pode salvar artista");
        }

        return ArtistaMapper.entityArtistaDTO(artistaSalvo);

    }

    public ArtistaDTO buscarArtistaPorId(Long id) {
        return artistaRepository.findById(id)
                .map(ArtistaMapper::entityArtistaDTO)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException(String.format("artista com %d não encontrado", id));
                });
    }

    public Page<ArtistaDTO> buscarTodosArtistas(Pageable pageable) {
        return artistaRepository.findAll(pageable)
                .map(ArtistaMapper::entityArtistaDTO);
    }

    public Page<ArtistaDTO> buscarArtistasPor(String nome, String nacionalidade, Pageable pageable) {
        if (Objects.isNull(nome) && Objects.isNull(nacionalidade)) {
            return buscarTodosArtistas(pageable);
        }

        var artistas = artistaRepository
                .findByNomeContainingIgnoreCaseOrNacionalidadeContainingIgnoreCase(pageable, nome, nacionalidade);

        var content = String.format(
                "{%s}", (nome != null ? "nome: " + nome  : "") + " " +
                        (nacionalidade != null ? "nacionalidade: " + nacionalidade : ""));

        if (artistas.isEmpty())
            throw new EntityNotFoundException(String.format("não foi encontrado artista(s): %s", content));

        return  artistas.map(ArtistaMapper::entityArtistaDTO);
    }

    public ArtistaDTO atualizarArtista(Long id, ArtistaCadastroDTO artista) {
        var artistaSalvo = artistaRepository.findById(id)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException(String.format("artista com %d não encontrado", id));
                });

        artistaSalvo.setNome(artista.getNome());
        artistaSalvo.setNacionalidade(artista.getNacionalidade());

        return ArtistaMapper
                .entityArtistaDTO(artistaRepository.save(artistaSalvo));
    }
}
