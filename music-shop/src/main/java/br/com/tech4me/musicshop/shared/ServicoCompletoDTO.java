package br.com.tech4me.musicshop.shared;

import br.com.tech4me.musicshop.model.FormatoMusical;
import br.com.tech4me.musicshop.model.Musica;
import br.com.tech4me.musicshop.model.Pagamento;
import br.com.tech4me.musicshop.model.Servico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ServicoCompletoDTO(
        String id,

        @NotBlank(message = "Nome do contratante está inválido!")
        String contratante,

        //O @Positive está verificando se o "valor" está acima de 0
        @Positive(message = "O valor precisa ser positivo") 
        Double valor,

        Pagamento metodoDePagamento,

        FormatoMusical formatoMusical,

        Musica musica) {

    public static ServicoCompletoDTO fromServicoCompleto(Servico servico, Musica musica) {
        return new ServicoCompletoDTO(servico.getId(), servico.getContratante(), servico.getValor(), servico.getMetodoDePagamento(), servico.getFormatoMusical(), musica);
    }
}
