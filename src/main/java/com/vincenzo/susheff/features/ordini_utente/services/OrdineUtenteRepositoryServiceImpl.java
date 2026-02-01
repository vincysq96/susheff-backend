package com.vincenzo.susheff.features.ordini_utente.services;

import com.vincenzo.susheff.features.ordini_utente.entities.OrdineUtenteEntity;
import com.vincenzo.susheff.features.ordini_utente.repositories.OrdineUtenteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrdineUtenteRepositoryServiceImpl implements OrdineUtenteRepositoryService {
  private final OrdineUtenteRepository ordineUtenteRepository;

  @Override
  public OrdineUtenteEntity getByIdOrThrow(Integer idOrdineUtente) {
    log.debug("RepoService - Recupero ordine utente con id: {}", idOrdineUtente);
    final OrdineUtenteEntity ordineUtente = ordineUtenteRepository.findById(idOrdineUtente)
      .orElseThrow(() -> {
        log.debug("RepoService - Ordine utente non trovato con id: {}", idOrdineUtente);
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordine utente non trovato con id: " + idOrdineUtente);
    });
    log.debug("RepoService - Ordine utente recuperato con id: {}", idOrdineUtente);
    return ordineUtente;
  }

  @Override
  public OrdineUtenteEntity save(OrdineUtenteEntity entityToSave) {
    log.debug("RepoService - Salvataggio ordine utente: {}", entityToSave);
    final OrdineUtenteEntity ordineUtenteSaved = ordineUtenteRepository.save(entityToSave);
    log.debug("RepoService - Ordine utente salvato: {}", ordineUtenteSaved);
    return ordineUtenteSaved;
  }

  @Override
  public void deleteEntity(OrdineUtenteEntity entityToDelete) {
    log.debug("RepoService - Cancellazione ordine utente: {}", entityToDelete);
    ordineUtenteRepository.delete(entityToDelete);
    log.debug("RepoService - Ordine utente cancellato: {}", entityToDelete);
  }

  @Override
  public void deleteById(Integer idOrdineUtente) {
    log.debug("RepoService - Cancellazione ordine utente con id: {}", idOrdineUtente);
    ordineUtenteRepository.deleteById(idOrdineUtente);
    log.debug("RepoService - Ordine utente cancellato con id: {}", idOrdineUtente);
  }
}
