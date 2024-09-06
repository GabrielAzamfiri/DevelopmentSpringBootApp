package com.example.DevelopmentSpringBootApp.repositories;


import com.example.DevelopmentSpringBootApp.entities.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository // Specializzazione di @Component dedicata al mondo dei DB
public interface EdificioRepository extends JpaRepository<Edificio, UUID> {


}
