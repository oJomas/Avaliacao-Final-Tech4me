package br.com.tech4me.musicshop.httpClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.tech4me.musicshop.model.Musica;


//O FeignClient criar clientes HTTP de forma simples!
@FeignClient("musica")
public interface MusicaClient {

    @RequestMapping(method = RequestMethod.GET, value = "/musicas/{id}")
    Musica getMusicById(@PathVariable String id);

}
