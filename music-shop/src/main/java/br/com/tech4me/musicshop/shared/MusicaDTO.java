package br.com.tech4me.musicshop.shared;

import br.com.tech4me.musicshop.model.GeneroMusical;
import br.com.tech4me.musicshop.model.Musica;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


//O DTO é usado para transferir dados e verificar se esses dados estão corretos com o "Validation"!
public record MusicaDTO(String id,

                        //Validando se não há um campo em branco nos dados ou nulo!
                        @NotBlank(message = "Está com caracteres em branco ou nulo!")
                        String nomeDaMusica,
                    
                        //Validando se não há um campo em branco nos dados ou nulo! 
                        @NotBlank(message = "Está com caracteres em branco ou nulo!")
                        String artista, 

                        GeneroMusical generoMusical,

                        //O @Positive está verificando se o "anolacamento" está acima de 0
                        @Positive
                        Integer anoLancamento) {
    
    public static MusicaDTO from(Musica music){
        return new MusicaDTO(music.getId(), music.getNomeDaMusica(), music.getArtista(), music.getGeneroMusical(), music.getAnoLancamento());
    }
}
