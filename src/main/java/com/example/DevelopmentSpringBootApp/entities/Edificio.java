package com.example.DevelopmentSpringBootApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import java.util.UUID;

@Entity
@Table(name = "edificio")
@Getter
@Setter
@NoArgsConstructor
public class Edificio {


    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE) // toglie il setter per l'id
    protected UUID id;


    @Column(name = "nome", nullable = false)
    protected String nome;

    @Column(name = "indirizzo", nullable = false)
    protected String indirizzo;

    @Column(name = "citta", nullable = false)
    protected String citta;

    @OneToMany(mappedBy = "edificio")
    private List<Postazione> nrPostazioni;

    public Edificio(String nome, String indirizzo, String citta) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.citta = citta;
    }

    @Override
    public String toString() {
        return "Edificio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", citta='" + citta + '\'' +
                '}';
    }
}
