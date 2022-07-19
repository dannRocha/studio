package com.album.musica.services;

import com.album.musica.dto.MusicaCadastroDTO;
import com.album.musica.dto.MusicaDTO;
import com.album.musica.entities.Musica;
import com.album.musica.expcetion.EntityNotFoundException;
import com.album.musica.mapper.MusicaMapper;
import com.album.musica.repositories.MusicaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class MusicaService {
    private final MusicaRepository repository;

    public MusicaDTO salvarMusica(MusicaCadastroDTO musica) {
        var musicaEntity = MusicaMapper.DTOCadastroToEntity(musica);
        return MusicaMapper.entityToDTO(repository.save(musicaEntity));
    }

    public MusicaDTO buscarMusicaPorId(Long id) {
        return repository.findById(id)
                .map(MusicaMapper::entityToDTO)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("música não encontrada");
                });
    }
    public Page<MusicaDTO> buscarTodasMusicas(Pageable paginacao) {
        return repository.findAll(paginacao)
                .map(MusicaMapper::entityToDTO);
    }

    public MusicaDTO atualizarMusica(Long id, MusicaCadastroDTO musica) {
        buscarMusicaPorId(id);
        var musicaEntity = MusicaMapper.DTOCadastroToEntity(musica);
        musicaEntity.setID(id);
        return MusicaMapper.entityToDTO(repository.save(musicaEntity));
    }
}
