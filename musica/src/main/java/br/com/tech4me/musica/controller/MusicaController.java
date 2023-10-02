package br.com.tech4me.musica.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.tech4me.musica.service.MusicaService;
import br.com.tech4me.musica.shared.MusicaDTO;
import jakarta.validation.Valid;

//O Controller é Respónsável pela comunicação entre a interface do usuário e o servidor.

// O RestController Define que essa aplicação é uma controller, poupando bastante do trabalho manual.
//O RequestMapping é usado para manipular alterações dinâmicas no URI.
@RestController
@RequestMapping("/musicas")
public class MusicaController {

    @Autowired
    private MusicaService service;

    //Esse "GET" vai pegar todas as músicas cadastradas
    @GetMapping
    public ResponseEntity<List<MusicaDTO>> getAllMusic(){
         //O ResponseEntity vai dar uma resposta no protocolo HTTP. Que nesse o 200 = "Ok"
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    //Nesse "GET" vai pega uma musica específica, definida pelo id!
    @GetMapping("/{id}")
    public ResponseEntity<MusicaDTO> getById(@PathVariable String id){
        //O PathVariable pega a váriavel que for colocada na url(normalmente o Id)
       Optional<MusicaDTO> musicaDto = service.getById(id);
        return musicaDto.isPresent() ? new ResponseEntity<>(musicaDto.get(), HttpStatus.OK)
         : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Alterando os dados de uma música já existente pelo id!
    @PutMapping("/{id}")
    public ResponseEntity<MusicaDTO> updateMusicById(@PathVariable String id, @RequestBody @Valid MusicaDTO musica){
        Optional<MusicaDTO> musicaAtualizar = service.updateById(id, musica);

        //Verificando, se a música e o id estão presente. Caso sim vai retornar a música atualizada, caso não, retorna não encontrado(NOT_FOUND)
        return musicaAtualizar.isPresent() ? new ResponseEntity<>(musicaAtualizar.get(), HttpStatus.OK): 
        new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Cadastrando uma nova música
    @PostMapping
    public ResponseEntity<MusicaDTO> registerMusic(@RequestBody @Valid MusicaDTO musica ){
        return new ResponseEntity<>(service.register(musica), HttpStatus.CREATED);
    }

    //Deletando uma música pelo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusicById(@PathVariable String id){
        service.deleteById(id);
        //No Content é o mesmo que sem conteudo!
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
   
}
