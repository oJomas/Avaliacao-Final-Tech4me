package br.com.tech4me.musicshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.musicshop.httpClient.MusicaClient;
import br.com.tech4me.musicshop.model.Musica;
import br.com.tech4me.musicshop.model.Servico;
import br.com.tech4me.musicshop.repository.ServicoRepository;
import br.com.tech4me.musicshop.shared.ServicoCompletoDTO;
import br.com.tech4me.musicshop.shared.ServicoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class Servico_ServiceImpl implements Servico_Service{

    @Autowired
    private ServicoRepository repository;

    //O MusicaClient faz a conexão entre o serviço e a música
    @Autowired
    private MusicaClient client;

    //Método para verificar todos os serviços
    @Override
    public List<ServicoDto> getAll() {
        return repository.findAll().stream().map(m -> ServicoDto.fromServicoDto(m)).toList();
    }

    
    //CircuitBreaker Vai monitorar o método getById, Caso falhe vai chamar o método fallbackGetById
    @CircuitBreaker(name = "getMusicById", fallbackMethod = "fallbackGetById")

    //Método para pegar pelo Id um serviço específico
    @Override
    public Optional<ServicoCompletoDTO> getById(String id) {
        Optional<Servico> servico = repository.findById(id);
        
        //Verificando se o id está presente nos dados! Caso estiver, retorna o método, caso não, retorna vázio!
        if(servico.isPresent()){
            Musica musica = client.getMusicById(servico.get().getMusica());
            return Optional.of(ServicoCompletoDTO.fromServicoCompleto(servico.get(), musica));
        }
    
        return Optional.empty();
    }

    //Caso a música não estiver cadastrada no service 
    public Optional<ServicoCompletoDTO> fallbackGetById(String id, Exception e){
        Optional<Servico> servico = repository.findById(id);

        if(servico.isPresent()){
            //A música fica nula na hora de chama o método
            Musica musica = null;
            return Optional.of(ServicoCompletoDTO.fromServicoCompleto(servico.get(), musica));
        }
        return Optional.empty();
    }



    //Método para cadastrar um novo serviço 
    @Override
    public ServicoDto register(ServicoDto servicoDto) {
        new Servico();
        Servico servico = new Servico().fromServicoDto(servicoDto);
        //Musica musica = client.getMusicById(servicoDto.musicaId());
        repository.save(servico);
        return ServicoDto.fromServicoDto(servico);
    }



    //Método para atualizar os dados de um serviço já existente
    @Override
    public Optional<ServicoDto> updateById(String id, ServicoDto servicoDto) {
        Optional<Servico> servico = repository.findById(id);
        
        //Verificando se o id existe
        if(servico.isPresent()){
            
            //Alterando os dados do serviço
            Servico servicoAtualizado = new Servico().fromServicoDto(servicoDto);
            //Salvando com o mesmo Id
            servicoAtualizado.setId(id);
            //Salvando no repositório
            repository.save(servicoAtualizado);
         
            //retornado um serviço alterado
            return Optional.of(ServicoDto.fromServicoDto(servicoAtualizado));
        }
        //Retornando vazio caso não houver o id
        return Optional.empty();
    }

    //Método para deletar um serviço pelo Id
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
    
}
