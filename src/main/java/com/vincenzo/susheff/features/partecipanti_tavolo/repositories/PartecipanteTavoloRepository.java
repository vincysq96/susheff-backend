package com.vincenzo.susheff.features.partecipanti_tavolo.repositories;

import com.vincenzo.susheff.common.data.enumerations.StatoTavoloEnum;
import com.vincenzo.susheff.features.partecipanti_tavolo.entities.PartecipanteTavoloEntity;
import com.vincenzo.susheff.features.tavoli.entities.TavoloEntity;
import com.vincenzo.susheff.features.utenti.entities.UtenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartecipanteTavoloRepository extends JpaRepository<PartecipanteTavoloEntity, Integer> {
  boolean existsByUtenteAndTavolo(UtenteEntity utente, TavoloEntity tavolo);
  void deleteByUtenteAndTavolo(UtenteEntity utente, TavoloEntity tavolo);
  Optional<PartecipanteTavoloEntity> findByUtente_IdUtenteAndTavolo_StatusTavolo(Integer idUtente, StatoTavoloEnum statusTavolo);

}