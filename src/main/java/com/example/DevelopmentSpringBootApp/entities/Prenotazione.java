package com.example.DevelopmentSpringBootApp.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prenotazione")
@Getter
@Setter
@NoArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE) // toglie il setter per l'id
    protected UUID id;


    @Column(name = "data_prenotazione", nullable = false)
    protected LocalDate dataPrenotazione;

    @ManyToOne
    @JoinColumn(name = "postazione")
    private Postazione postazione;

    @ManyToOne
    @JoinColumn(name = "utente")
    private Utente utente;

    public Prenotazione(LocalDate dataPrenotazione, Postazione postazione, Utente utente) {
        this.dataPrenotazione = dataPrenotazione;
        this.postazione = postazione;
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", dataPrenotazione=" + dataPrenotazione +
                ", postazione=" + postazione +
                ", utente=" + utente.nomeCognome +
                '}';
    }
}
