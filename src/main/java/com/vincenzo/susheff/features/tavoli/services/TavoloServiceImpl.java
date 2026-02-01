package com.vincenzo.susheff.features.tavoli.services;

import com.vincenzo.susheff.features.ordini_tavolo.services.OrdineTavoloService;
import com.vincenzo.susheff.features.tavoli.dto.TavoloResponseDTO;
import com.vincenzo.susheff.features.tavoli.entities.TavoloEntity;
import com.vincenzo.susheff.features.tavoli.mappers.TavoloMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TavoloServiceImpl implements TavoloService {
  private final TavoloRepositoryService tavoloRepositoryService;
  private final OrdineTavoloService ordineTavoloService;

  @Override
  @Transactional
  public TavoloResponseDTO openTable(String codiceTavolo) {
    log.debug("Service - Apertura tavolo con codice: {}", codiceTavolo);
    checkTavoloNonEsistente(codiceTavolo);
    TavoloEntity entityToSave = TavoloMapper.createEntity(codiceTavolo);
    final TavoloEntity entitySaved = tavoloRepositoryService.save(entityToSave);
    ordineTavoloService.creaOrdineTavolo(entitySaved);
    log.debug("Service - Tavolo aperto con codice: {}", codiceTavolo);
    return TavoloMapper.entityToDTO(entitySaved);
  }

  @Override
  @Transactional
  public TavoloResponseDTO closeTable(String codiceTavolo) {
    log.debug("Service - Chiusura tavolo con codice: {}", codiceTavolo);
    TavoloEntity existingEntity = tavoloRepositoryService.getByCodiceTavoloOrThrow(codiceTavolo);
    TavoloEntity entityToSave = TavoloMapper.closeTable(existingEntity);
    final TavoloEntity entitySaved = tavoloRepositoryService.save(entityToSave);
    log.debug("Service - Tavolo chiuso con codice: {}", codiceTavolo);
    return TavoloMapper.entityToDTO(entitySaved);
  }

  @Override
  @Transactional(readOnly = true)
  public List<TavoloResponseDTO> getOpenTables() {
    log.debug("Service - Recupero tavoli aperti");
    final List<TavoloEntity> openTables = tavoloRepositoryService.getOpenTables();
    log.debug("Service - Tavoli aperti recuperati: {}", openTables.size());
    return openTables.stream()
      .map(TavoloMapper::entityToDTO)
      .toList();
  }

  @Override
  @Transactional
  public void deleteByCodice(String codiceTavolo) {
    log.debug("Service - Cancellazione tavolo con codice: {}", codiceTavolo);
    TavoloEntity existingEntity = tavoloRepositoryService.getByCodiceTavoloOrThrow(codiceTavolo);
    tavoloRepositoryService.deleteTavolo(existingEntity);
    log.debug("Service - Tavolo con codice: {} cancellato", codiceTavolo);
  }

  private void checkTavoloNonEsistente(String codiceTavolo) {
    if (tavoloRepositoryService.existsByCodiceTavolo(codiceTavolo)) {
      throw new ResponseStatusException(
        HttpStatus.CONFLICT,
        "Tavolo con codice: " + codiceTavolo + " già esistente."
      );
    }
  }

}
