package br.com.tech4me.musicshop.shared;


import br.com.tech4me.musicshop.model.FormatoMusical;
import br.com.tech4me.musicshop.model.Pagamento;
import br.com.tech4me.musicshop.model.Servico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ServicoDto(
    String id,

    @NotBlank(message = "Nome do contratante está inválido!")
    String contratante,
    
    //O @Positive está verificando se o "valor" está acima de 0
    @Positive(message = "O valor precisa ser positivo")
    Double valor,

    Pagamento metodoDePagamento,

    FormatoMusical formatoMusical,

    String musicaId){

    public static ServicoDto fromServicoDto(Servico servico){
        return new ServicoDto(servico.getId(), servico.getContratante(), servico.getValor(), servico.getMetodoDePagamento(), servico.getFormatoMusical(), servico.getMusica());
    }
    
}
