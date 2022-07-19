package com.album.musica.services;

import com.album.musica.dto.AlbumCadastroDTO;
import com.album.musica.dto.AlbumDTO;
import com.album.musica.expcetion.EntityNotFoundException;
import com.album.musica.mapper.AlbumMapper;
import com.album.musica.repositories.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class AlbumService {

    private final AlbumRepository repository;
    public AlbumDTO salvarAlbum(AlbumCadastroDTO album) {
        var albumEntity = AlbumMapper.DTOCadastroToEntity(album);
        return AlbumMapper.entityToDTO(repository.save(albumEntity));
    }

    public Page<?> buscarTodosAlbuns(Pageable pageable, String campo) {
        return repository.findAllAndOrder(pageable)
                .map(AlbumMapper::entityToDTO);
    }

    public AlbumDTO buscarAlbumPorId(Long id) {
        return repository.findById(id)
                .map(AlbumMapper::entityToDTO)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("album não encontrado");
                });
    }

    public AlbumDTO atualizarAlbum(Long id, AlbumCadastroDTO album) {
        var albumSalvo = repository.findById(id)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("album não encontrado");
                });
        var albumEntity = AlbumMapper.DTOCadastroToEntity(album);

        albumSalvo.getParticipantes().addAll(albumEntity.getParticipantes());
        albumSalvo.setAno(albumEntity.getAno());
        albumSalvo.setNome(albumEntity.getNome());

        return AlbumMapper.entityToDTO(repository.save(albumSalvo));
    }
}

