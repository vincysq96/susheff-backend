package com.vincenzo.susheff.features.partecipanti_tavolo.services;


import com.vincenzo.susheff.features.partecipanti_tavolo.entities.PartecipanteTavoloEntity;
import com.vincenzo.susheff.features.tavoli.entities.TavoloEntity;
import com.vincenzo.susheff.features.utenti.entities.UtenteEntity;

public interface PartecipanteTavoloRepositoryService {
  boolean existsByUtenteAndTavolo(UtenteEntity utente, TavoloEntity tavolo);
  PartecipanteTavoloEntity save(PartecipanteTavoloEntity entityToSave);
  void deleteByUtenteAndTavolo(UtenteEntity existingUtente, TavoloEntity existingTavolo);
  TavoloEntity getTavoloAttivoPerUtente(Integer idUtente);
}
