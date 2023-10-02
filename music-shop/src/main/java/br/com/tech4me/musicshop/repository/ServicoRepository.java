package br.com.tech4me.musicshop.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.tech4me.musicshop.model.Servico;

@Repository
public interface ServicoRepository extends MongoRepository<Servico, String> {
    
}
