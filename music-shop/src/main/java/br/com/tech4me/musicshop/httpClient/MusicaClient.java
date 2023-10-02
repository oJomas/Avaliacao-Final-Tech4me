package br.com.tech4me.musicshop.httpClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.tech4me.musicshop.model.Musica;
import br.com.tech4me.musicshop.shared.MusicaDTO;

//O FeignClient criar clientes HTTP de forma simples!
@FeignClient(name = "musicaClient")
public interface MusicaClient {

    @RequestMapping(method = RequestMethod.GET, value = "/musicas/{id}")
    MusicaDTO getServiceById(@PathVariable String id);
    
}
