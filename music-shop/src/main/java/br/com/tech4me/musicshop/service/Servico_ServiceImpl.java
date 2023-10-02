package br.com.tech4me.musicshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.musicshop.httpClient.MusicaClient;
import br.com.tech4me.musicshop.model.Musica;
import br.com.tech4me.musicshop.model.Servico;
import br.com.tech4me.musicshop.repository.ServicoRepository;
import br.com.tech4me.musicshop.shared.MusicaDTO;
import br.com.tech4me.musicshop.shared.ServicoCompletoDTO;
import br.com.tech4me.musicshop.shared.ServicoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class Servico_ServiceImpl implements Servico_Service{

    @Autowired
    ServicoRepository repository;

    @Autowired
    MusicaClient client;

    //@CircuitBreaker faz o suporte para tolerar as falhas projetadas
    @CircuitBreaker(name = "GetAllService", fallbackMethod = "fallbackGetAllSevice")
    @Override
    //método para verificar todos os serviços
    public List<ServicoCompletoDTO> getAll() {
        return repository.findAll().stream().map(m -> ServicoCompletoDTO.from(m)).toList();
    }

    //@CircuitBreaker faz o suporte para tolerar as falhas projetadas
    @CircuitBreaker(name = "GetServiceById", fallbackMethod = "fallbackGetById")
    @Override
    //Método para verificar pelo Id um serviço específico
    public Optional<ServicoCompletoDTO> getById(String id) {
        
        //Criando uma variável da classe musica e pegando o id escolhido!
        //var = Verificação automática do tipo do dado, reduzindo o código repetitivo!
        Optional<Servico> servico = repository.findById(id);
        
        //Verificando se o id está presente nos dados! Caso estiver retorna o método, caso não retorna vázio!
        return servico.isPresent() ? Optional.of(ServicoCompletoDTO.from(servico.get())) : Optional.empty();
    }


    //@CircuitBreaker faz o suporte para tolerar as falhas projetadas nesse caso vai ser no "register"
    @CircuitBreaker(name = "register", fallbackMethod = "fallbackRegister")
    @Override
    //Método para cadastrar um novo serviço 
    public ServicoCompletoDTO register(ServicoDto servicoDto) {
        Servico servico = Servico.fromServicoDto(servicoDto);
        MusicaDTO musicaDto = client.getServiceById(servicoDto.musicaId());
        servico.setMusica(Musica.fromMusicaDTO(musicaDto));
        repository.save(servico);
        return ServicoCompletoDTO.from(servico);
    }

    //@CircuitBreaker faz o suporte para tolerar as falhas projetadas nesse caso vai ser no "updateRegisterById"
    @CircuitBreaker(name = "updateRegisterById", fallbackMethod = "fallbackUpdateRegisterById")
    @Override

    //Método para atualizar os dados de um serviço já existente
    public Optional<ServicoCompletoDTO> updateById(String id, ServicoCompletoDTO servicoDto) {
        Optional<Servico> servico = repository.findById(id);

        //Verificando se o id existe
        if(servico.isPresent()){
            //Alterando os dados do serviço
            Servico servicoAtualizado = Servico.fromServicoCompletoDTO(servicoDto);
            repository.save(servicoAtualizado);

            //retornado um serviço alterada
            return Optional.of(ServicoCompletoDTO.from(servicoAtualizado));
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
