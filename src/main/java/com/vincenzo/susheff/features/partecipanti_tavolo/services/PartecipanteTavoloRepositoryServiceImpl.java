package com.vincenzo.susheff.features.partecipanti_tavolo.services;

import com.vincenzo.susheff.common.data.enumerations.StatoTavoloEnum;
import com.vincenzo.susheff.features.partecipanti_tavolo.entities.PartecipanteTavoloEntity;
import com.vincenzo.susheff.features.partecipanti_tavolo.repositories.PartecipanteTavoloRepository;
import com.vincenzo.susheff.features.tavoli.entities.TavoloEntity;
import com.vincenzo.susheff.features.utenti.entities.UtenteEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
@Service
public class PartecipanteTavoloRepositoryServiceImpl implements PartecipanteTavoloRepositoryService {

  private final PartecipanteTavoloRepository partecipanteTavoloRepository;

  @Override
  public TavoloEntity getTavoloAttivoPerUtente(Integer idUtente){
    log.debug("RepoService - Recupero tavolo attivo per utente con id: {}", idUtente);
    final TavoloEntity tavoloAttivo = partecipanteTavoloRepository.findByUtente_IdUtenteAndTavolo_StatusTavolo(idUtente, StatoTavoloEnum.APERTO)
      .map(PartecipanteTavoloEntity::getTavolo)
      .orElseThrow(() -> {
        log.debug("RepoService - Tavolo attivo per utente con id: {} non trovato", idUtente);
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Tavolo attivo non trovato per utente con id: " + idUtente);
      });
    log.debug("RepoService - Tavolo attivo per utente con id: {} recuperato: {}", idUtente, tavoloAttivo);
    return tavoloAttivo;
  }
  @Override
  public boolean existsByUtenteAndTavolo(UtenteEntity utente, TavoloEntity tavolo) {
    log.debug("RepoService - Verifica esistenza partecipante tavolo per utente: {} e tavolo: {}", utente, tavolo);
    final boolean exists = partecipanteTavoloRepository.existsByUtenteAndTavolo(utente, tavolo);
    log.debug("RepoService - Esistenza partecipante tavolo per utente: {} e tavolo: {} è {}", utente, tavolo, exists);
    return exists;
  }

  @Override
  public PartecipanteTavoloEntity save(PartecipanteTavoloEntity entityToSave) {
    log.debug("RepoService - Salvataggio partecipante al tavolo: {}", entityToSave);
    final PartecipanteTavoloEntity partecipanteTavoloSaved = partecipanteTavoloRepository.save(entityToSave);
    log.debug("RepoService - Partecipante al tavolo salvato: {}", partecipanteTavoloSaved);
    return partecipanteTavoloSaved;
  }

  @Override
  public void deleteByUtenteAndTavolo(UtenteEntity utente, TavoloEntity tavolo) {
    log.debug("RepoService - Cancellazione partecipante al tavolo per utente: {} e tavolo: {}", utente, tavolo);
    partecipanteTavoloRepository.deleteByUtenteAndTavolo(utente, tavolo);
    log.debug("RepoService - Partecipante al tavolo cancellato per utente: {} e tavolo: {}", utente, tavolo);
  }

}

