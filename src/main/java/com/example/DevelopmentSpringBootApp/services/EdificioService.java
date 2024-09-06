package com.example.DevelopmentSpringBootApp.services;



import com.example.DevelopmentSpringBootApp.entities.Edificio;
import com.example.DevelopmentSpringBootApp.exceptions.NotFoundException;
import com.example.DevelopmentSpringBootApp.repositories.EdificioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class EdificioService {
    @Autowired
    private EdificioRepository edificioRepository;


    public void saveEdificio(Edificio edificio){
        try {
            edificioRepository.save(edificio);
            log.info("L'edificio " + edificio.getNome() + " salvato con successo!");
        }catch (Exception e){
            log.error("Errore generico nel saveEdificio  " + e.getMessage());
        }

    }

    public List<Edificio> findAllEdifici(){
        try {
            return edificioRepository.findAll();
        }catch (Exception e){
            log.error("Errore generico nel findAllEdifici  " + e.getMessage());
            return List.of();
        }

    }

    public Edificio findEdificioById(UUID edificioId){
        try {
            return edificioRepository.findById(edificioId).orElseThrow(() -> new NotFoundException(edificioId));
        }catch (Exception e){
            log.error("Errore generico nel findEdificioById  " + e.getMessage());
            return null;
        }

    }

    public void findByIdAndDelete(UUID edificioId){
        try {
            Edificio found = this.findEdificioById(edificioId);
            edificioRepository.delete(found);
            log.info("L'edificio con id " + edificioId + " cancellato correttamente!");
        }catch (Exception e){
            log.error("Errore generico nel findByIdAndDelete  " + e.getMessage());
        }

    }

    public void findByIdAndUpdate(UUID edificioId, Edificio updatedEdificio){
        try {
            Edificio found = this.findEdificioById(edificioId);

            found.setNome(updatedEdificio.getNome());
            found.setCitta(updatedEdificio.getCitta());
            found.setIndirizzo(updatedEdificio.getIndirizzo());
            edificioRepository.save(found);

            log.info("L'edificio con id " + edificioId + " modificato correttamente!");
        }catch (Exception e){
            log.error("Errore generico nel findByIdAndUpdate  " + e.getMessage());
        }

    }



}
