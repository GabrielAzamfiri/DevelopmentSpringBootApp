package com.example.DevelopmentSpringBootApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "utente")
@Getter
@Setter

@NoArgsConstructor
public class Utente {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE) // toglie il setter per l'id
    protected UUID id;


    @Column(name = "username", nullable = false)
    protected String username;

    @Column(name = "nome_cognome", nullable = false)
    protected String nomeCognome;

    @Column(name = "email", nullable = false)
    protected String email;

    @OneToMany(mappedBy = "utente")
    private List<Prenotazione> nrPrenotazioni;


    public Utente(String username, String nomeCognome, String email) {
        this.username = username;
        this.nomeCognome = nomeCognome;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nomeCognome='" + nomeCognome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
