package com.example.DevelopmentSpringBootApp.services;



import com.example.DevelopmentSpringBootApp.entities.Prenotazione;
import com.example.DevelopmentSpringBootApp.exceptions.NotFoundException;
import com.example.DevelopmentSpringBootApp.repositories.PrenotazioneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;


    public void saveprenotazione(Prenotazione prenotazione){
        try {
            List<Prenotazione>  prenotazioni = prenotazioneRepository.findByPostazioneAndDataPrenotazione(prenotazione.getPostazione(),prenotazione.getDataPrenotazione() );
            //  boolean postazioneGiaPrenotata = prenotazioni.stream().anyMatch(preno -> preno.getDataPrenotazione().equals(prenotazione.getDataPrenotazione()));
            List<Prenotazione> prenotazionniUtente = prenotazioneRepository.findByUtenteAndDataPrenotazione(prenotazione.getUtente(),prenotazione.getDataPrenotazione());
            if (prenotazioni.isEmpty() && prenotazionniUtente.isEmpty()) {
                prenotazioneRepository.save(prenotazione);
                System.out.println("La prenotazione " + prenotazione.getId() + " salvata con successo!");
            }else {
                System.err.println("La postazione scelta è già stata prenotata nella data richiesta oppure l'utente ha già una prenotazione per questo giorno!!");
            }
        }catch (Exception e){
            log.error("Errore generico nel saveprenotazione " + e.getMessage());

        }


    }

    public List<Prenotazione> findAllPrenotazioni(){
       try {
           return prenotazioneRepository.findAll();

       } catch (Exception e){
           log.error("Errore generico nel findAllPrenotazioni " + e.getMessage());
           return List.of();
       }

    }

    public Prenotazione findPrenotazioneById(UUID prenotazioneId){
        try {
            return prenotazioneRepository.findById(prenotazioneId).orElseThrow(() -> new NotFoundException(prenotazioneId));

        } catch (Exception e){
            System.err.println("Errore generico nel findPrenotazioneById " + e.getMessage());
            return null;
        }

    }

    public void findByIdAndDelete(UUID prenotazioneId){
        try {
            Prenotazione found = this.findPrenotazioneById(prenotazioneId);
            prenotazioneRepository.delete(found);
            log.info("La prenotazione " + prenotazioneId + " cancellata correttamente!");
        }catch (Exception e){
            log.error("Errore generico nel findByIdAndDelete " + e.getMessage());
        }

    }

    public void findByIdAndUpdate(UUID prenotazioneId, Prenotazione updatedPrenotazione){
        try {
            Prenotazione found = this.findPrenotazioneById(prenotazioneId);

            found.setDataPrenotazione(updatedPrenotazione.getDataPrenotazione());
            found.setPostazione(updatedPrenotazione.getPostazione());
            found.setUtente(updatedPrenotazione.getUtente());
            prenotazioneRepository.save(found);

            log.info("La prenotazione " + prenotazioneId + " modificato correttamente!");
        }catch (Exception e){
            log.error("Errore generico nel findByIdAndUpdate " + e.getMessage());
        }

    }



}
