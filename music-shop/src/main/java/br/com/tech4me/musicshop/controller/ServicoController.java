package br.com.tech4me.musicshop.controller;

import java.util.List;

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

import br.com.tech4me.musicshop.service.Servico_Service;
import br.com.tech4me.musicshop.shared.ServicoCompletoDTO;
import br.com.tech4me.musicshop.shared.ServicoDto;
import jakarta.validation.Valid;

//O Controller é Respónsável pela comunicação entre a interface do usuário e o servidor.
// O RestController Define que essa aplicação é uma controller, poupando bastante do trabalho manual.
//O RequestMapping é usado para manipular alterações dinâmicas no URI.
@RequestMapping("/servicos")
@RestController
public class ServicoController {
    
    @Autowired
    Servico_Service servico;


    //Verificando todos os serviços
    @GetMapping
    public ResponseEntity<List<ServicoCompletoDTO>> getAllService(){
        return new ResponseEntity<>(servico.getAll(), HttpStatus.OK);
    }


    //Verificando pelo Id um serviço específico
    @GetMapping("/{id}")
    public ResponseEntity<ServicoCompletoDTO> getServiceById(@PathVariable String id){

        return servico.getById(id).isPresent() ? new ResponseEntity<>(servico.getById(id).get(), HttpStatus.OK)
         : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Cadastrando um serviço novo
    @PostMapping
    public ResponseEntity<ServicoCompletoDTO> registerService(@Valid @RequestBody ServicoDto servicoDto){
        return new ResponseEntity<>(servico.register(servicoDto), HttpStatus.CREATED);
    }

    //Alterando os dados de serviço já existente pelo Id
    @PutMapping("/{id}")
    public ResponseEntity<ServicoCompletoDTO> updateServiceById(@PathVariable String id, @Valid @RequestBody ServicoDto service){

        //Verificando, se o serviço e o id estão presente. Caso sim vai pode registrar outro serviço, caso não, retorna não encontrado(NOT_FOUND)
        return servico.getById(id).isPresent() ? new ResponseEntity<>(servico.register(service), HttpStatus.OK): 
        new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

      //Deletando um serviço pelo Id
      @DeleteMapping("/{id}")
      public ResponseEntity<Void> deleteService(@PathVariable String id){
        servico.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
      }
}
