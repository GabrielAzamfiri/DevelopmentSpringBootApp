package com.example.DevelopmentSpringBootApp.services;



import com.example.DevelopmentSpringBootApp.entities.Edificio;
import com.example.DevelopmentSpringBootApp.exceptions.NotFoundException;
import com.example.DevelopmentSpringBootApp.repositories.EdificioRepository;
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
public class EdificioService {
    @Autowired
    private EdificioRepository edificioRepository;


    public void saveEdificio(Edificio edificio){
        edificioRepository.save(edificio);
        log.info("L'edificio " + edificio.getNome() + " salvato con successo!");
    }

    public List<Edificio> findAllEdifici(){
        return edificioRepository.findAll();
    }

    public Edificio findEdificioById(UUID edificioId){
        return edificioRepository.findById(edificioId).orElseThrow(() -> new NotFoundException(edificioId));
    }

    public void findByIdAndDelete(UUID edificioId){
        Edificio found = this.findEdificioById(edificioId);
        edificioRepository.delete(found);
        log.info("L'edificio con id " + edificioId + " cancellato correttamente!");
    }

    public void findByIdAndUpdate(UUID edificioId, Edificio updatedEdificio){

        Edificio found = this.findEdificioById(edificioId);

        found.setNome(updatedEdificio.getNome());
        found.setCitta(updatedEdificio.getCitta());
        found.setIndirizzo(updatedEdificio.getIndirizzo());
        edificioRepository.save(found);

        log.info("L'edificio con id " + edificioId + " modificato correttamente!");
    }



}
