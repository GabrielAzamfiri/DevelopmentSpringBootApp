package com.example.DevelopmentSpringBootApp.services;




import com.example.DevelopmentSpringBootApp.entities.Postazione;
import com.example.DevelopmentSpringBootApp.enums.TipoPostazione;
import com.example.DevelopmentSpringBootApp.exceptions.NotFoundException;
import com.example.DevelopmentSpringBootApp.repositories.PostazioneRepository;
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
public class PostazioneService {
    @Autowired
    private PostazioneRepository postazioneRepository;


    public void savePostazione(Postazione postazione){
        try {
            postazioneRepository.save(postazione);
            log.info("La postazione " + postazione.getId() + " salvata con successo!");
        }catch (Exception e){
            log.error("Errore generico nel savePostazione  " + e.getMessage());
        }

    }

    public List<Postazione> findAllPostazioni(){
        try {
            return postazioneRepository.findAll();

        }catch (Exception e){
            log.error("Errore generico nel findAllPostazioni  " + e.getMessage());
            return List.of();
        }

    }

    public Postazione findPostazioneById(UUID postazioneId){
        try {
            return postazioneRepository.findById(postazioneId).orElseThrow(() -> new NotFoundException(postazioneId));
        }catch (Exception e){
            log.error("Errore generico nel findPostazioneById  " + e.getMessage());
            return null;
        }
    }

    public void findByIdAndDelete(UUID postazioneId){
        try {
            Postazione found = this.findPostazioneById(postazioneId);
            postazioneRepository.delete(found);
            log.info("La postazione " + postazioneId + " cancellato correttamente!");
        }catch (Exception e){
            log.error("Errore generico nel findByIdAndDelete  " + e.getMessage());
        }

    }

    public void findByIdAndUpdate(UUID postazioneId, Postazione updatedPostazione){
        try {
            Postazione found = this.findPostazioneById(postazioneId);

            found.setDescrizione(updatedPostazione.getDescrizione());
            found.setEdificio(updatedPostazione.getEdificio());
            found.setTipoPostazione(updatedPostazione.getTipoPostazione());
            found.setMaxOccupanti(updatedPostazione.getMaxOccupanti());

            postazioneRepository.save(found);

            log.info("La postazione " + postazioneId + " modificato correttamente!");
        }catch (Exception e){
            log.error("Errore generico nel findByIdAndUpdate  " + e.getMessage());
        }

    }

    public List<Postazione> findPostazioniByTipoAndCitta(TipoPostazione tipoPostazione, String citta){
        try {
            return postazioneRepository.findByTipoPostazioneAndEdificioCitta(tipoPostazione,citta);
        }catch (Exception e){
            log.error("Errore generico nel findPostazioniByTipoAndCitta  " + e.getMessage());
            return List.of();
        }

    }

}
