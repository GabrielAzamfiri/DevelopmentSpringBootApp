package com.example.DevelopmentSpringBootApp.repositories;


import com.example.DevelopmentSpringBootApp.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository // Specializzazione di @Component dedicata al mondo dei DB
public interface UtenteRepository extends JpaRepository<Utente, UUID> {



    //controllare che le parole siano giuste findBy Nome(perche l'argometno è salvato come nome) StartingWith
   // List<Prodotto> findByNomeStartingWith(String partialName);
    // SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT(:partialName, '%'))
}
