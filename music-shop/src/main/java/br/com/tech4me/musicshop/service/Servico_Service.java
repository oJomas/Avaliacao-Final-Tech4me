package br.com.tech4me.musicshop.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.musicshop.shared.ServicoCompletoDTO;
import br.com.tech4me.musicshop.shared.ServicoDto;

public interface Servico_Service {

        //Método para pegar todos os serviços!
        List<ServicoDto> getAll();

        //Método para listar um serviço específico!
        Optional<ServicoCompletoDTO> getById(String id);
    
        //Método para cadastrar um serviço!
        ServicoDto register(ServicoDto servicoDto);
    
        //Método que atualiza um serviço cadastrado
        Optional<ServicoDto> updateById(String id, ServicoDto ServicoDto);
        
        //Método para excluir um serviço pelo Id
        void deleteById(String id);
    
}
