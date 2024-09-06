package com.example.DevelopmentSpringBootApp.entities;

import com.example.DevelopmentSpringBootApp.enums.TipoPostazione;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "postazione")
@Getter
@Setter
@NoArgsConstructor
public class Postazione {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE) // toglie il setter per l'id
    private UUID id;

    @Column(name = "descrizione", nullable = false)
    private String descrizione;

    @Column(name = "tipo_postazione", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPostazione tipoPostazione;

    @Column(name = "max_occupanti", nullable = false)
    private int maxOccupanti;

    @ManyToOne
    @JoinColumn(name = "edificio")
    private Edificio edificio;

    @OneToMany(mappedBy = "postazione")
    private List<Prenotazione> nrPrenotazioni;

    public Postazione(String descrizione, TipoPostazione tipoPostazione, int maxOccupanti, Edificio edificio) {
        this.descrizione = descrizione;
        this.tipoPostazione = tipoPostazione;
        this.maxOccupanti = maxOccupanti;
        this.edificio = edificio;
    }

    @Override
    public String toString() {
        return "Postazione{" +
                "id=" + id +
                ", descrizione='" + descrizione + '\'' +
                ", tipoPostazione=" + tipoPostazione +
                ", maxOccupanti=" + maxOccupanti +
                ", edificio=" + edificio +
                '}';
    }
}
