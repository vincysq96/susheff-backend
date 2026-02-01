package com.vincenzo.susheff.features.ordini_utente.entities;

import com.vincenzo.susheff.features.ordini_tavolo.entities.OrdineTavoloEntity;
import com.vincenzo.susheff.features.utenti.entities.UtenteEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ordini_utente", schema = "susheff")
@NoArgsConstructor
public class OrdineUtenteEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_ordine_utente")
  private Integer idOrdineUtente;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_ordine_tavolo", nullable = false)
  private OrdineTavoloEntity ordineTavolo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_utente", nullable = false)
  private UtenteEntity utente;

  @Column(name = "codice_piatto", nullable = false)
  private String codicePiatto;

  @Column(name = "quantita", nullable = false)
  private Integer quantita;

  @Column(name = "notes")
  private String notes;

  @PrePersist
  protected void onCreate() {
    if (quantita == null) {
      quantita = 1;
    }
  }

  public OrdineUtenteEntity(OrdineTavoloEntity ordineTavolo, UtenteEntity utente, String codicePiatto, Integer quantita, String notes) {
    this.ordineTavolo = ordineTavolo;
    this.utente = utente;
    this.codicePiatto = codicePiatto;
    this.quantita = quantita;
    this.notes = notes;
  }
}
