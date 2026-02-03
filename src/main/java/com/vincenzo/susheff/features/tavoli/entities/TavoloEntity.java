package com.vincenzo.susheff.features.tavoli.entities;

import com.vincenzo.susheff.common.data.enumerations.StatoTavoloEnum;
import com.vincenzo.susheff.features.ordini_tavolo.entities.OrdineTavoloEntity;
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
@Table(name = "tavoli")
@NoArgsConstructor
public class TavoloEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_tavolo")
  private Integer idTavolo;

  @Column(name = "codice_tavolo", nullable = false)
  private String codiceTavolo;

  @Column(name = "data_apertura", nullable = false)
  private LocalDateTime dataApertura;

  @Column(name = "data_chiusura")
  private LocalDateTime dataChiusura;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private StatoTavoloEnum statusTavolo;

  @OneToMany(mappedBy = "tavolo", fetch = FetchType.LAZY)
  private Set<OrdineTavoloEntity> ordini = new HashSet<>();

  @OneToMany(mappedBy = "tavolo", fetch = FetchType.LAZY)
  private Set<PartecipanteTavoloEntity> partecipantiTavolo = new HashSet<>();

  @PrePersist
  protected void onCreate() {
    if (dataApertura == null) {
      dataApertura = LocalDateTime.now();
    }
    if (statusTavolo == null) {
      statusTavolo = StatoTavoloEnum.APERTO;
    }
  }

  public TavoloEntity(String codiceTavolo) {
    this.codiceTavolo = codiceTavolo;
  }
}