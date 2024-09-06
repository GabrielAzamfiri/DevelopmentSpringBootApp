package com.example.DevelopmentSpringBootApp;




import com.example.DevelopmentSpringBootApp.entities.Edificio;
import com.example.DevelopmentSpringBootApp.entities.Postazione;
import com.example.DevelopmentSpringBootApp.entities.Prenotazione;
import com.example.DevelopmentSpringBootApp.entities.Utente;
import com.example.DevelopmentSpringBootApp.services.EdificioService;
import com.example.DevelopmentSpringBootApp.services.PostazioneService;
import com.example.DevelopmentSpringBootApp.services.PrenotazioneService;
import com.example.DevelopmentSpringBootApp.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;


@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private EdificioService edificioService;

    @Autowired
    private PostazioneService postazioneService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private UtenteService utenteService;





    @Override
    public void run(String... args) throws Exception {
        System.out.println("Inizio Run");


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DevelopmentSpringBootAppApplication.class);

        Edificio edificio1 = context.getBean("edificio1", Edificio.class);
        Edificio edificio2 = context.getBean("edificio2", Edificio.class);
        Postazione postazionePrivata1 = context.getBean("postazionePrivata1", Postazione.class);
        Postazione postazionePrivata2 = context.getBean("postazionePrivata2", Postazione.class);
        Postazione postazioneOpenSpace1 = context.getBean("postazioneOpenSpace1", Postazione.class);
        Postazione postazioneOpenSpace2 = context.getBean("postazioneOpenSpace2", Postazione.class);
        Postazione postazioneSalaRiunioni1 = context.getBean("postazioneSalaRiunioni1", Postazione.class);
        Postazione postazioneSalaRiunioni2 = context.getBean("postazioneSalaRiunioni2", Postazione.class);

//        edificioService.saveEdificio(edificio1);
//        edificioService.saveEdificio(edificio2);
//        postazioneService.savePostazione(postazionePrivata1);
//        postazioneService.savePostazione(postazionePrivata2);
//        postazioneService.savePostazione(postazioneOpenSpace1);
//        postazioneService.savePostazione(postazioneOpenSpace2);
//        postazioneService.savePostazione(postazioneSalaRiunioni1);
//        postazioneService.savePostazione(postazioneSalaRiunioni2);

        Utente utenteDiego= new Utente("DigoBasi","Diego Basili", "diegobasili@gmail.com");
        Utente utenteEddy= new Utente("Edyan","Eddy Turpo", "eddyturpo@gmail.com");
//        utenteService.saveUtente(utenteDiego);
//        utenteService.saveUtente(utenteEddy);


        Postazione postazionePrivata1FromDB =  postazioneService.findPostazioneById(UUID.fromString("5eba77af-6047-4315-adf2-25bb85c24f96"));
        Postazione postazionePrivata2FromDB =  postazioneService.findPostazioneById(UUID.fromString("3482c249-54d3-4de9-b9ab-eb3ec51a139b"));
        Postazione postazioneOpenSpace1FromDB =  postazioneService.findPostazioneById(UUID.fromString("6f5df944-01f2-421e-88ad-0d788953deec"));
        Postazione postazioneOpenSpace2FromDB =  postazioneService.findPostazioneById(UUID.fromString("71aca0e2-6506-436b-8909-cd5f3f27ff29"));
        Postazione postazioneSalaRiunioni1FromDB =  postazioneService.findPostazioneById(UUID.fromString("d6d10caf-fe26-42fb-9435-bb0912a1fcbd"));
        Postazione postazioneSalaRiunioni2FromDB =  postazioneService.findPostazioneById(UUID.fromString("289f0e52-072c-4363-ae70-6aa1d083ca79"));

        Utente utenteDiegoFromDB =  utenteService.findUtenteById(UUID.fromString("a71e89be-f6c1-43e9-bbca-867cc52bf861"));
        Utente utenteEddyFromDB =  utenteService.findUtenteById(UUID.fromString("b85d5e9e-94ba-4b64-8a2b-cfdb56988aa3"));

        Prenotazione prenotazione1= new Prenotazione(LocalDate.of(2024,9,6), postazionePrivata1FromDB, utenteDiegoFromDB);
        Prenotazione prenotazione2= new Prenotazione(LocalDate.of(2024,9,7), postazionePrivata2FromDB, utenteEddyFromDB);
        Prenotazione prenotazione3= new Prenotazione(LocalDate.of(2024,9,8), postazioneOpenSpace2FromDB, utenteEddyFromDB);
        Prenotazione prenotazione4= new Prenotazione(LocalDate.of(2024,9,9), postazioneSalaRiunioni1FromDB, utenteDiegoFromDB);
        prenotazioneService.saveprenotazione(prenotazione1);
        prenotazioneService.saveprenotazione(prenotazione2);
        prenotazioneService.saveprenotazione(prenotazione3);
        prenotazioneService.saveprenotazione(prenotazione4);

    }
}
