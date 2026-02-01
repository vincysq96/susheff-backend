package com.vincenzo.susheff.features.tavoli.services;

import com.vincenzo.susheff.common.data.enumerations.StatoTavoloEnum;
import com.vincenzo.susheff.features.tavoli.entities.TavoloEntity;
import com.vincenzo.susheff.features.tavoli.repositories.TavoloRepository;
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
public class TavoloRepositoryServiceImpl implements TavoloRepositoryService {
  private final TavoloRepository tavoloRepository;

  @Override
  public TavoloEntity save(TavoloEntity entityToSave) {
    log.debug("RepoService - Salvataggio tavolo: {}", entityToSave);
    final TavoloEntity tavoloSaved = tavoloRepository.save(entityToSave);
    log.debug("RepoService - Tavolo salvato: {}", tavoloSaved);
    return tavoloSaved;
  }

  @Override
  public TavoloEntity getByCodiceTavoloOrThrow(String codiceTavolo) {
    log.debug("RepoService - Recupero tavolo per codice: {}", codiceTavolo);
    final TavoloEntity tavolo = tavoloRepository.findByCodiceTavolo(codiceTavolo)
      .orElseThrow(() -> {
        log.debug("RepoService - Tavolo con codice non trovato: {}", codiceTavolo);
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Tavolo non trovato con codice: " + codiceTavolo);
      });
    log.debug("RepoService - Tavolo con codice recuperato: {}", tavolo);
    return tavolo;
  }

  @Override
  public boolean existsByCodiceTavolo(String codiceTavolo) {
    return tavoloRepository.existsByCodiceTavolo(codiceTavolo);
  }

  @Override
  public List<TavoloEntity> getOpenTables() {
    log.debug("RepoService - Recupero tavoli aperti");
    final LocalDateTime dataLimite = LocalDateTime.now().minusHours(2);
    final List<TavoloEntity> openTables = tavoloRepository.findByAndStatusTavoloAndDataAperturaAfter(StatoTavoloEnum.APERTO, dataLimite);
    log.debug("RepoService - Tavoli aperti recuperati: {}", openTables);
    return openTables;
  }

  @Override
  public void deleteTavolo(TavoloEntity tavoloToDelete) {
    log.debug("RepoService - Cancellazione tavolo con codice: {}", tavoloToDelete.getIdTavolo());
    tavoloRepository.delete(tavoloToDelete);
    log.debug("RepoService - Tavolo cancellato con codice: {}", tavoloToDelete.getIdTavolo());
  }

  @Override
  public Integer puliziaTavoli(LocalDateTime dataLimite) {
    log.debug("RepoService - Pulizia tavoli aperti prima di: {}", dataLimite);
    final List<TavoloEntity> oldOpenTables = tavoloRepository.findByDataAperturaBefore(dataLimite);
    Integer countOldOpenTables = oldOpenTables.size();
    tavoloRepository.deleteAll(oldOpenTables);
    log.debug("RepoService - Tavoli vecchi cancellati: {}", countOldOpenTables);
    return countOldOpenTables;
  }
}
