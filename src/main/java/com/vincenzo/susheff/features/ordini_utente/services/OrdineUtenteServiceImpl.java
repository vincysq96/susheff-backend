package com.vincenzo.susheff.features.ordini_utente.services;

import com.vincenzo.susheff.features.ordini_tavolo.entities.OrdineTavoloEntity;
import com.vincenzo.susheff.features.ordini_tavolo.services.OrdineTavoloRepositoryService;
import com.vincenzo.susheff.features.ordini_utente.dto.OrdineUtenteRequestCreateDTO;
import com.vincenzo.susheff.features.ordini_utente.dto.OrdineUtenteRequestUpdateDTO;
import com.vincenzo.susheff.features.ordini_utente.dto.OrdineUtenteResponseDTO;
import com.vincenzo.susheff.features.ordini_utente.entities.OrdineUtenteEntity;
import com.vincenzo.susheff.features.ordini_utente.mappers.OrdineUtenteMapper;
import com.vincenzo.susheff.features.utenti.entities.UtenteEntity;
import com.vincenzo.susheff.features.utenti.services.UtenteRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrdineUtenteServiceImpl implements OrdineUtenteService {
  private final OrdineUtenteRepositoryService ordineUtenteRepositoryService;
  private final UtenteRepositoryService utenteRepositoryService;
  private final OrdineTavoloRepositoryService ordineTavoloRepositoryService;

  @Override
  @Transactional(readOnly = true)
  public OrdineUtenteResponseDTO getOrdineUtente(Integer idOrdineUtente) {
    log.debug("Service - Recupero ordine utente con id: {}", idOrdineUtente);
    final OrdineUtenteEntity existingOrdineUtente = ordineUtenteRepositoryService.getByIdOrThrow(idOrdineUtente);
    log.debug("Service - Ordine utente recuperato con id: {}", idOrdineUtente);
    return OrdineUtenteMapper.entityToDTO(existingOrdineUtente);
  }

  @Override
  @Transactional
  public OrdineUtenteResponseDTO creaOrdineUtente(Integer idOrdineTavolo, Integer idOrdineUtente, OrdineUtenteRequestCreateDTO requestCreate) {
    log.debug("Service - Creazione ordine utente con dati: {}", requestCreate);
    final OrdineTavoloEntity existingTavolo = ordineTavoloRepositoryService.getByIdOrThrow(idOrdineTavolo);
    final UtenteEntity existingUtente = utenteRepositoryService.getById(idOrdineUtente);
    final OrdineUtenteEntity newOrdineUtente = OrdineUtenteMapper.createNewEntity(requestCreate, existingTavolo, existingUtente);
    final OrdineUtenteEntity savedOrdineUtente = ordineUtenteRepositoryService.save(newOrdineUtente);
    log.debug("Service - Ordine utente creato con id: {}", savedOrdineUtente.getIdOrdineUtente());
    return OrdineUtenteMapper.entityToDTO(savedOrdineUtente);
  }

  @Transactional
  @Override
  public OrdineUtenteResponseDTO modificaOrdineUtente(Integer idOrdineUtente, OrdineUtenteRequestUpdateDTO requestUpdate) {
    log.debug("Service - Modifica ordine utente con id: {} e dati: {}", idOrdineUtente, requestUpdate);
    final OrdineUtenteEntity existingOrdineUtente = ordineUtenteRepositoryService.getByIdOrThrow(idOrdineUtente);
    final OrdineUtenteEntity ordineUtenteToUpdate = OrdineUtenteMapper.updateExistingEntity(requestUpdate, existingOrdineUtente);
    final OrdineUtenteEntity updatedOrdineUtente = ordineUtenteRepositoryService.save(ordineUtenteToUpdate);
    log.debug("Service - Ordine utente modificato con id: {}", updatedOrdineUtente.getIdOrdineUtente());
    return OrdineUtenteMapper.entityToDTO(updatedOrdineUtente);
  }

  @Transactional
  @Override
  public void eliminaOrdineUtente(Integer idOrdineUtente) {
    log.debug("Service - Eliminazione ordine utente con id: {}", idOrdineUtente);
    final OrdineUtenteEntity existingOrdineUtente = ordineUtenteRepositoryService.getByIdOrThrow(idOrdineUtente);
    ordineUtenteRepositoryService.deleteEntity(existingOrdineUtente);
    log.debug("Service - Ordine utente eliminato con id: {}", idOrdineUtente);
    OrdineUtenteMapper.entityToDTO(existingOrdineUtente);
  }
}
