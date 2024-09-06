package com.example.DevelopmentSpringBootApp.repositories;


import com.example.DevelopmentSpringBootApp.entities.Postazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository // Specializzazione di @Component dedicata al mondo dei DB
public interface PostazioneRepository extends JpaRepository<Postazione, UUID> {




}
