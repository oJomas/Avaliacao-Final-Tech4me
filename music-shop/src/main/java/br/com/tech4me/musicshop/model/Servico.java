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

    private String contratante;
    private Double valor;
    private Pagamento metodoDePagamento; 
    private FormatoMusical FormatoMusical;
    private String musica;

    public Servico fromServicoDto(ServicoDto servicoDto){
        Servico servico = new Servico();
        servico.setId(servicoDto.id());
        servico.setContratante(servicoDto.contratante());
        servico.setValor(servicoDto.valor());
        servico.setMetodoDePagamento(servicoDto.metodoDePagamento());
        servico.setFormatoMusical(servicoDto.formatoMusical());
        servico.setMusica(servicoDto.musicaId());
        return servico;
    }

    public Servico fromServicoCompletoDTO(ServicoCompletoDTO servicoDto){
        Servico servico = new Servico();
        servico.setId(servicoDto.id());
        servico.setContratante(servicoDto.contratante());
        servico.setValor(servicoDto.valor());
        servico.setMetodoDePagamento(servicoDto.metodoDePagamento());
        servico.setFormatoMusical(servicoDto.formatoMusical());
        servico.setMusica(servicoDto.musica().getId()); 
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
    
    public Pagamento getMetodoDePagamento() {
        return metodoDePagamento;
    }

    public void setMetodoDePagamento(Pagamento metodoDePagamento) {
        this.metodoDePagamento = metodoDePagamento;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public FormatoMusical getFormatoMusical() {
        return FormatoMusical;
    }

    public void setFormatoMusical(FormatoMusical formatoMusical) {
        FormatoMusical = formatoMusical;
    }

    public String getContratante() {
        return contratante;
    }

    public void setContratante(String contratante) {
        this.contratante = contratante;
    }

}
