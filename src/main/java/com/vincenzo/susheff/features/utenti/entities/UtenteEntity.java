package com.vincenzo.susheff.features.utenti.entities;

import com.vincenzo.susheff.features.partecipanti_tavolo.entities.PartecipanteTavoloEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "utenti")
@NoArgsConstructor
public class UtenteEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_utente")
  private Integer idUtente;

  @Column(name = "nome", nullable = false)
  private String nomeUtente;

  @Column(name = "data_creazione", nullable = false)
  private LocalDateTime dataCreazione;

  @OneToMany(mappedBy = "utente", fetch = FetchType.LAZY)
  private Set<PartecipanteTavoloEntity> partecipantiTavolo = new HashSet<>();

  public UtenteEntity(String nomeUtente) {
    this.nomeUtente = nomeUtente;
  }

  @PrePersist
  protected void onCreate() {
    if (dataCreazione == null) {
      dataCreazione = LocalDateTime.now();
    }
  }
}
