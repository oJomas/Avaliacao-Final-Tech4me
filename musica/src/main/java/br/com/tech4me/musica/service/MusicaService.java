package br.com.tech4me.musica.service;
import java.util.List;
import java.util.Optional;

import br.com.tech4me.musica.shared.MusicaDTO;
import jakarta.validation.Valid;

public interface MusicaService {

    //Método para pegar todos!
    List<MusicaDTO> getAll();

    //Método para listar um específico!
    Optional<MusicaDTO> getById(String id);

    //Método para cadastrar uma música!
    MusicaDTO register(@Valid MusicaDTO musicaDto);

    //Método que atualiza um música cadastrada!
    Optional<MusicaDTO> updateById(String id, @Valid MusicaDTO musicaDto);
    
    //Método para excluir uma música da lista
    void deleteById(String id);
}
