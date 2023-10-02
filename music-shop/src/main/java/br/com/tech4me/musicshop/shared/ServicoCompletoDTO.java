package br.com.tech4me.musicshop.shared;

import br.com.tech4me.musicshop.model.Musica;
import br.com.tech4me.musicshop.model.Pagamento;
import br.com.tech4me.musicshop.model.Servico;
import br.com.tech4me.musicshop.model.TempoDeAssinatura;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

public record ServicoCompletoDTO(
        String id,

        //O @Positive está verificando se o "valor" está acima de 0
        @Positive(message = "O valor precisa ser positivo") 
        Double valor,
        
        TempoDeAssinatura assinatura,

        Pagamento metodoDePagamento,

        //@Valid verifica se os dados da classe "Musica" está correto
        @Valid
        Musica musica) {

    public static ServicoCompletoDTO from(Servico servico) {
        return new ServicoCompletoDTO(servico.getId(),
                servico.getValor(), servico.getAssinatura(), servico.getMetodoDePagamento(), servico.getMusica());
    }
}
