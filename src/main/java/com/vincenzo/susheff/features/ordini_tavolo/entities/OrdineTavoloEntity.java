package com.vincenzo.susheff.features.ordini_tavolo.entities;

import com.vincenzo.susheff.common.data.enumerations.StatoOrdineEnum;
import com.vincenzo.susheff.features.ordini_utente.entities.OrdineUtenteEntity;
import com.vincenzo.susheff.features.tavoli.entities.TavoloEntity;
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
@Table(name = "ordini_tavolo", schema = "susheff")
@NoArgsConstructor
public class OrdineTavoloEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_ordine_tavolo")
  private Integer idOrdineTavolo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_tavolo", nullable = false)
  private TavoloEntity tavolo;

  @Enumerated(EnumType.STRING)
  @Column(name = "status_ordine", nullable = false)
  private StatoOrdineEnum statusOrdine;

  @Column(name = "round_ordinazione")
  private Integer roundOrdinazione;

  @Column(name = "data_ordine")
  private LocalDateTime dataOrdine;

  @OneToMany(mappedBy = "ordineTavolo", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<OrdineUtenteEntity> ordiniUtente = new HashSet<>();

  public OrdineTavoloEntity(TavoloEntity tavolo, Integer roundOrdinazione) {
    this.tavolo = tavolo;
    this.roundOrdinazione = roundOrdinazione;
  }

  @PrePersist
  protected void onCreate() {
    if (dataOrdine == null) {
      dataOrdine = LocalDateTime.now();
    }
    if (statusOrdine == null) {
      statusOrdine = StatoOrdineEnum.NUOVO;
    }
    if (roundOrdinazione == null) {
      roundOrdinazione = 1;
    }
  }
}