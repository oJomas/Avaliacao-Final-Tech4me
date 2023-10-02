package br.com.tech4me.musica.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.tech4me.musica.model.Musica;

//Repository é responsável por atua com na camada intermediária entre a aplicação e o banco de dados!
@Repository
public interface MusicaRepository extends MongoRepository<Musica, String>{
    
}
