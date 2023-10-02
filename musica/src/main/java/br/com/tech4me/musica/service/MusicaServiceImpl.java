package br.com.tech4me.musica.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.musica.model.Musica;
import br.com.tech4me.musica.repository.MusicaRepository;
import br.com.tech4me.musica.shared.MusicaDTO;

//O Service é reponsável pelas regras de negócio para o funcionamento da aplicação!
@Service
public class MusicaServiceImpl implements MusicaService{

    //O Autowired faz a injeção de dependências automáticamente!
    //As dependências são as bibliotecas externas que a aplicação vai utilizar.
    @Autowired
    MusicaRepository repository;

    //Método para pegar todas as músicas cadastradas!
    @Override
    public List<MusicaDTO> getAll() {
        return repository.findAll().stream().map(m -> MusicaDTO.from(m)).toList();
    }

    //Método para pegar uma música específica
    @Override
    public Optional<MusicaDTO> getById(String id) {
        //Criando uma variável da classe musica e pegando o id escolhido!
        //var = Verificação automática do tipo do dado, reduzindo o código repetitivo!
        var musica = repository.findById(id);
        
        //Verificando se o id está presente nos dados! Caso estiver retorna o método, caso não retorna vázio!
        return repository.findById(id).isPresent() ? Optional.of(MusicaDTO.from(musica.get())) : Optional.empty();    
    }

    
    //Método para cadastrar uma música nova
    @Override
    public MusicaDTO register(MusicaDTO musicaDto) {
        var musica = Musica.fromMusicaDTO(musicaDto);
        repository.save(musica);
        return MusicaDTO.from(musica);
    }


    //Método para atualizar uma música já existente pelo Id
    @Override
    public Optional<MusicaDTO> updateById(String id, MusicaDTO musicaDto) {
        Optional<Musica> musica = repository.findById(id);

        //Verificando se o id existe
        if(musica.isPresent()){
            //Alterando os dados da música
            Musica musicaAtualizado = Musica.fromMusicaDTO(musicaDto);
            repository.save(musicaAtualizado);

            //retornado a música alterada
            return Optional.of(MusicaDTO.from(musicaAtualizado));
        }
        //Retornando vazio caso não houver o id
        return Optional.empty();
    }


    //Método para deletar uma música pelo Id
    @Override
    public void deleteById(String id) {
       repository.deleteById(id);
    }
    
}
