package com.vincenzo.susheff.features.utenti.services;

import com.vincenzo.susheff.features.utenti.entities.UtenteEntity;
import com.vincenzo.susheff.features.utenti.repositories.UtenteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UtenteRepositoryServiceImpl implements UtenteRepositoryService{
  private final UtenteRepository utenteRepository;

  @Override
  public UtenteEntity save(UtenteEntity entityToSave) {
    log.debug("RepoService - Salvataggio utente: {}", entityToSave);
    final UtenteEntity utenteSaved = utenteRepository.save(entityToSave);
    log.debug("RepoService - Utente salvato: {}", utenteSaved);
    return utenteSaved;
  }

  @Override
  public UtenteEntity getById(Integer idUtente) {
    log.debug("RepoService - Recupero utente per id: {}", idUtente);
    final UtenteEntity utente = utenteRepository.findById(idUtente)
        .orElseThrow(() -> {
          log.debug("RepoService - Utente non trovato: {}", idUtente);
          return new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato con id: " + idUtente);
        });
    log.debug("RepoService - Utente recuperato: {}", utente);
    return utente;
  }

  @Override
  public UtenteEntity getByNomeUtenteOrThrow(String nomeUtente) {
    log.debug("RepoService - Recupero utente con nome: {}", nomeUtente);

    final UtenteEntity utente = utenteRepository.findByNomeUtente(nomeUtente)
      .orElseThrow(() -> {
        log.debug("RepoService - Utente con nome {} non trovato", nomeUtente);
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato con nome: " + nomeUtente);
      });

    log.debug("RepoService - Utente con nome {} recuperato", utente);
    return utente;
  }

  @Override
  public void deleteById(Integer idUtente) {
    log.debug("RepoService - Cancellazione utente per id: {}", idUtente);
    utenteRepository.deleteById(idUtente);
    log.debug("RepoService - Utente cancellato per id: {}", idUtente);
  }

  @Override
  public Integer puliziaUtenti(LocalDateTime dataLimite) {
    log.debug("RepoService - Pulizia utenti creati prima di: {}", dataLimite);
    final List<UtenteEntity> oldUtenti = utenteRepository.findByDataCreazioneBefore(dataLimite);
    Integer countOldUtenti = oldUtenti.size();
    utenteRepository.deleteAll(oldUtenti);
    log.debug("RepoService - Utenti vecchi cancellati: {}", countOldUtenti);
    return countOldUtenti;
  }

  @Override
  public boolean existsByNomeUtente(String nomeUtente) {
    return utenteRepository.existsByNomeUtente(nomeUtente);
  }

}
