package br.com.tech4me.musicshop.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.tech4me.musicshop.shared.ServicoCompletoDTO;
import br.com.tech4me.musicshop.shared.ServicoDto;

@Document("servico")
public class Servico {
    
    @Id
    private String id;
    //Definindo o valor de um serviço
    private Double valor;
    //O tempo da assinatura do serviço!
    private TempoDeAssinatura assinatura;
    //O método que o cliente vai pagar
    private Pagamento metodoDePagamento; 

    //Conectando com a coluna música
    private Musica musica;

    public static Servico fromServicoDto(ServicoDto servicoDto){
        Servico servico = new Servico();
        servico.setId(servicoDto.id());
        servico.setValor(servicoDto.valor());
        servico.setAssinatura(servicoDto.assinatura());
        servico.setMetodoDePagamento(servicoDto.metodoDePagamento());
        return servico;
    }

    public static Servico fromServicoCompletoDTO(ServicoCompletoDTO servicoDto){
        Servico servico = new Servico();
        servico.setId(servicoDto.id());
        servico.setValor(servicoDto.valor());
        servico.setAssinatura(servicoDto.assinatura());
        servico.setMetodoDePagamento(servicoDto.metodoDePagamento());
        servico.setMusica(servicoDto.musica()); 
        return servico;
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    public Musica getMusica() {
        return musica;
    }
    public void setMusica(Musica musica) {
        this.musica = musica;
    }
    public TempoDeAssinatura getAssinatura() {
        return assinatura;
    }
    public void setAssinatura(TempoDeAssinatura assinatura) {
        this.assinatura = assinatura;
    }

    public Pagamento getMetodoDePagamento() {
        return metodoDePagamento;
    }

    public void setMetodoDePagamento(Pagamento metodoDePagamento) {
        this.metodoDePagamento = metodoDePagamento;
    }


}
