package com.example.DevelopmentSpringBootApp.services;

import com.example.DevelopmentSpringBootApp.entities.Utente;
import com.example.DevelopmentSpringBootApp.exceptions.NotFoundException;
import com.example.DevelopmentSpringBootApp.exceptions.ValidationException;
import com.example.DevelopmentSpringBootApp.repositories.UtenteRepository;
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
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;


    public void saveUtente(Utente utente){
        try{
            if(utente.getNomeCognome().length() < 2) throw new ValidationException("NomeCognome troppo corto!");
            utenteRepository.save(utente);
            log.info("L'utente " + utente.getNomeCognome() + " salvato con successo!");
        } catch (Exception e){
            log.error("Errore generico nel salvataggio utente " + e.getMessage());
        }
    }

    public List<Utente> findAllUtenti(){
        try {
            return utenteRepository.findAll();

        } catch (Exception e){
            log.error("Errore generico nel findAllUtenti " + e.getMessage());
            return List.of();
        }

    }

    public Utente findUtenteById(UUID utenteId){
        try {
            return utenteRepository.findById(utenteId).orElseThrow(() -> new NotFoundException(utenteId));

        }catch (Exception e){
            log.error("Errore generico nel findUtenteById " + e.getMessage());
            return null;
        }

    }

    public void findByIdAndDelete(UUID utenteId){
       try {
           Utente found = this.findUtenteById(utenteId);
           utenteRepository.delete(found);
           log.info("L'utente con id " + utenteId + " cancellato correttamente!");
       }catch (Exception e){
           log.error("Errore generico nel findByIdAndDelete " + e.getMessage());
       }

    }

    public void findByIdAndUpdate(UUID utenteId, Utente updatedUtente){
        try {
            Utente found = this.findUtenteById(utenteId);

            found.setEmail(updatedUtente.getEmail());
            found.setUsername(updatedUtente.getUsername());
            found.setNomeCognome(updatedUtente.getNomeCognome());
            utenteRepository.save(found);

            log.info("L'utente con id " + utenteId + " modificato correttamente!");
        }catch (Exception e){
            log.error("Errore generico nel findByIdAndUpdate " + e.getMessage());
        }

    }



}
