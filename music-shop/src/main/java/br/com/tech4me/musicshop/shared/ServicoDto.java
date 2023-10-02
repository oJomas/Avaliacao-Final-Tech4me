package br.com.tech4me.musicshop.shared;


import br.com.tech4me.musicshop.model.Pagamento;
import br.com.tech4me.musicshop.model.Servico;
import br.com.tech4me.musicshop.model.TempoDeAssinatura;

import jakarta.validation.constraints.Positive;

public record ServicoDto(
    String id,

    //O @Positive está verificando se o "valor" está acima de 0
    @Positive(message = "O valor precisa ser positivo")
    Double valor,

    TempoDeAssinatura assinatura,

    Pagamento metodoDePagamento,

    String musicaId){

    public static ServicoDto from(Servico servico){
        return new ServicoDto(servico.getId(),
        servico.getValor(), servico.getAssinatura(), servico.getMetodoDePagamento(), servico.getMusica().getId());
    }
    
}
