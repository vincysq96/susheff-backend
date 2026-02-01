package com.vincenzo.susheff.features.ordini_tavolo.services;

import com.vincenzo.susheff.common.data.enumerations.StatoOrdineEnum;
import com.vincenzo.susheff.features.ordini_tavolo.entities.OrdineTavoloEntity;
import com.vincenzo.susheff.features.ordini_tavolo.repositories.OrdineTavoloRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrdineTavoloRepositoryServiceImpl implements OrdineTavoloRepositoryService {
  private final OrdineTavoloRepository ordineTavoloRepository;

  @Override
  public OrdineTavoloEntity save(OrdineTavoloEntity entityToSave) {
    log.debug("RepoService - Salvataggio ordine tavolo: {}", entityToSave);
    final OrdineTavoloEntity ordineTavoloSaved = ordineTavoloRepository.save(entityToSave);
    log.debug("RepoService - Ordine tavolo salvato: {}", ordineTavoloSaved);
    return ordineTavoloSaved;
  }

  @Override
  public OrdineTavoloEntity getUltimoOrdineTavoloInCorsoOrThrow(String codiceTavolo) {
    log.debug("RepoService - Recupero ultimo ordine tavolo in corso per tavolo con codice: {}", codiceTavolo);
    final OrdineTavoloEntity ordineTavolo = ordineTavoloRepository.findTopByTavolo_CodiceTavoloAndStatusOrdineOrderByDataOrdineDesc(codiceTavolo, StatoOrdineEnum.NUOVO)
      .orElseThrow(() -> {
        log.error("RepoService - Ordine tavolo in corso non trovato per tavolo con codice: {}", codiceTavolo);
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordine tavolo in corso non trovato per tavolo con codice: " + codiceTavolo);
      });
    log.debug("RepoService - Ultimo ordine tavolo in corso per tavolo con codice: {} recuperato", codiceTavolo);
    return ordineTavolo;
  }

  @Override
  public OrdineTavoloEntity getByIdOrThrow(Integer idOrdineTavolo) {
    log.debug("RepoService - Recupero ordine tavolo con id: {}", idOrdineTavolo);
    final OrdineTavoloEntity ordineTavolo = ordineTavoloRepository.findById(idOrdineTavolo)
      .orElseThrow(() -> {
        log.error("RepoService - Ordine tavolo con id: {} non trovato", idOrdineTavolo);
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordine tavolo con id: " + idOrdineTavolo + " non trovato");
      });
    log.debug("RepoService - Ordine tavolo recuperato: {}", idOrdineTavolo);
    return ordineTavolo;
  }
}
