package br.com.tech4me.musicshop.model;

import br.com.tech4me.musicshop.shared.MusicaDTO;

//As Classes Model é responsável por armazenar os dados da aplicação!
public class Musica {

    private String id;

    //Variável do nome da música!
    private String nomeDaMusica;

    //Artista da música
    private String artista;

    //O "GeneroMusical" é uma classe enum, criado para armazena o genêro da música!
    private GeneroMusical generoMusical;

    //O Ano da música
    private Integer anoLancamento;
   
    //Construtor, que armazena os dados da classe "Musica"
    public static Musica fromMusicaDTO(MusicaDTO musicaDto){
        Musica musica = new Musica();
        musica.setId(musicaDto.id());
        musica.setNomeDaMusica(musicaDto.nomeDaMusica());
        musica.setArtista(musicaDto.artista());
        musica.setGeneroMusical(musicaDto.generoMusical());
        musica.setAnoLancamento(musicaDto.anoLancamento()); 
        return musica;
    }

    //Getters:
    public String getId() {
        return id;
    }
    public String getNomeDaMusica() {
        return nomeDaMusica;
    }
    public String getArtista() {
        return artista;
    }
    public GeneroMusical getGeneroMusical() {
        return generoMusical;
    }


    //Setters
    public void setId(String id) {
        this.id = id;
    }
    public Integer getAnoLancamento() {
        return anoLancamento;
    }
    public void setArtista(String artista) {
        this.artista = artista;
    }
    public void setGeneroMusical(GeneroMusical generoMusical) {
        this.generoMusical = generoMusical;
    }
    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
    public void setNomeDaMusica(String nomeDaMusica) {
        this.nomeDaMusica = nomeDaMusica;
    }

}


