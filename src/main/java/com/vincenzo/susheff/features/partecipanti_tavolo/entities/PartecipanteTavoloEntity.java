package com.vincenzo.susheff.features.partecipanti_tavolo.entities;

import com.vincenzo.susheff.features.tavoli.entities.TavoloEntity;
import com.vincenzo.susheff.features.utenti.entities.UtenteEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "partecipanti_tavolo")
@NoArgsConstructor
public class PartecipanteTavoloEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_partecipante")
  private Integer idPartecipante;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_utente", nullable = false)
  private UtenteEntity utente;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_tavolo", nullable = false)
  private TavoloEntity tavolo;

  public PartecipanteTavoloEntity(UtenteEntity utente, TavoloEntity tavolo) {
    this.utente = utente;
    this.tavolo = tavolo;
  }
}