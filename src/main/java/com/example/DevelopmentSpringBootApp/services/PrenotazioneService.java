package com.example.DevelopmentSpringBootApp.services;



import com.example.DevelopmentSpringBootApp.entities.Edificio;
import com.example.DevelopmentSpringBootApp.entities.Prenotazione;
import com.example.DevelopmentSpringBootApp.exceptions.NotFoundException;
import com.example.DevelopmentSpringBootApp.repositories.EdificioRepository;
import com.example.DevelopmentSpringBootApp.repositories.PrenotazioneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service // Service è un'altra specializzazione di @Component
// Il service è una classe che ci consente di poter aggiungere ulteriore logica personalizzata durante le varie interazioni col database.
// Ad esempio quando salvo un nuovo record, prima di salvarlo posso effettuare tutta una serie di controlli di validazione dei suoi attributi
// oppure potrei magari aggiungere ulteriori attributi
@Slf4j
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;


    public void saveprenotazione(Prenotazione prenotazione){
        prenotazioneRepository.save(prenotazione);
        log.info("La prenotazione " + prenotazione.getId() + " salvata con successo!");
    }

    public List<Prenotazione> findAllPrenotazioni(){
        return prenotazioneRepository.findAll();
    }

    public Prenotazione findPrenotazioneById(UUID prenotazioneId){
        return prenotazioneRepository.findById(prenotazioneId).orElseThrow(() -> new NotFoundException(prenotazioneId));
    }

    public void findByIdAndDelete(UUID prenotazioneId){
        Prenotazione found = this.findPrenotazioneById(prenotazioneId);
        prenotazioneRepository.delete(found);
        log.info("La prenotazione " + prenotazioneId + " cancellata correttamente!");
    }

    public void findByIdAndUpdate(UUID prenotazioneId, Prenotazione updatedPrenotazione){

        Prenotazione found = this.findPrenotazioneById(prenotazioneId);

        found.setDataPrenotazione(updatedPrenotazione.getDataPrenotazione());
        found.setPostazione(updatedPrenotazione.getPostazione());
        found.setUtente(updatedPrenotazione.getUtente());
        prenotazioneRepository.save(found);

        log.info("La prenotazione " + prenotazioneId + " modificato correttamente!");
    }



}
