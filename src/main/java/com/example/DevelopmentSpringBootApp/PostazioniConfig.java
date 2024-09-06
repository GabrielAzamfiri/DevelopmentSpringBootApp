package com.example.DevelopmentSpringBootApp;




import com.example.DevelopmentSpringBootApp.entities.Edificio;
import com.example.DevelopmentSpringBootApp.entities.Postazione;
import com.example.DevelopmentSpringBootApp.enums.TipoPostazione;
import org.springframework.context.annotation.*;

@Configuration // Annotazione OBBLIGATORIA se vogliamo che questa classe venga presa in considerazione all'avvio dell'applicazione
@PropertySource("application.properties")
public class PostazioniConfig {


    @Bean
    public Edificio edificio1(){
        return new Edificio("Edificio1","Indirizzo1","Cinisello Balsamo");
    }
    @Bean
    public Edificio edificio2(){
        return new Edificio("Edificio2","Indirizzo2","Monza");
    }

    @Bean
    public Postazione postazionePrivata1(){
        return new Postazione("Postazione1 One to One", TipoPostazione.PRIVATO,2,edificio1());
    }
    @Bean
    public Postazione postazionePrivata2(){
        return new Postazione("Postazione2 One to One", TipoPostazione.PRIVATO,2,edificio2());
    }
    @Bean
    public Postazione postazioneOpenSpace1(){
        return new Postazione("Postazione1 Tutti per Tutti", TipoPostazione.OPENSPACE,10,edificio1());
    }
    @Bean
    public Postazione postazioneOpenSpace2(){
        return new Postazione("Postazione2 Tutti per Tutti", TipoPostazione.OPENSPACE,10,edificio2());
    }
    @Bean
    public Postazione postazioneSalaRiunioni1(){
        return new Postazione("Postazione1 Roba Nostra", TipoPostazione.SALA_RIUNIONI,6,edificio1());
    }
    @Bean
    public Postazione postazioneSalaRiunioni2(){
        return new Postazione("Postazione2 Roba Nostra", TipoPostazione.SALA_RIUNIONI,6,edificio2());
    }


}
