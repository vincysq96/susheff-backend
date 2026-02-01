package com.vincenzo.susheff.features.utenti.services;

import com.vincenzo.susheff.features.partecipanti_tavolo.services.PartecipanteTavoloRepositoryService;
import com.vincenzo.susheff.features.tavoli.dto.TavoloResponseDTO;
import com.vincenzo.susheff.features.tavoli.entities.TavoloEntity;
import com.vincenzo.susheff.features.tavoli.mappers.TavoloMapper;
import com.vincenzo.susheff.features.utenti.dto.UtenteRequestUpdateDTO;
import com.vincenzo.susheff.features.utenti.dto.UtenteResponseDTO;
import com.vincenzo.susheff.features.utenti.entities.UtenteEntity;
import com.vincenzo.susheff.features.utenti.mappers.UtenteMapper;
import com.vincenzo.susheff.features.utenti.repositories.UtenteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
@Service
public class UtenteServiceImpl implements UtenteService{
  private final UtenteRepository utenteRepository;
  private final UtenteRepositoryService utenteRepositoryService;
  private final PartecipanteTavoloRepositoryService partecipanteTavoloRepositoryService;

  @Override
  @Transactional(readOnly = true)
  public TavoloResponseDTO getTavoloAttivoPerUtente(Integer idUtente){
    log.debug("Service - Recupero tavolo attivo per utente con ID: {}", idUtente);
    final TavoloEntity tavoloAttivo = partecipanteTavoloRepositoryService.getTavoloAttivoPerUtente(idUtente);
    log.debug("Service - Tavolo attivo recuperato per utente con ID: {}", idUtente);
    return TavoloMapper.entityToDTO(tavoloAttivo);

  }

  @Override
  @Transactional
  public UtenteResponseDTO create(String nomeUtente) {
    log.debug("Service - Creazione utente con nome: {}", nomeUtente);
    if (utenteRepositoryService.existsByNomeUtente(nomeUtente)) {
      throw new ResponseStatusException(
        HttpStatus.CONFLICT,
        "Utente con nome: " + nomeUtente + " già esistente."
      );
    }
    UtenteEntity entityToSave = new UtenteEntity(nomeUtente);
    final UtenteEntity utenteSaved = utenteRepository.save(entityToSave);
    log.debug("Service - Utente creato con ID: {}", utenteSaved.getIdUtente());
    return UtenteMapper.entityToDTO(utenteSaved);
  }

  @Override
  @Transactional
  public UtenteResponseDTO updateById(Integer idUtente, UtenteRequestUpdateDTO requestUpdateDTO) {
    log.debug("Service - Aggiornamento utente con dati: {}", requestUpdateDTO);
    final UtenteEntity existingUtente = utenteRepositoryService.getById(idUtente);
    UtenteEntity utenteToUpdate = UtenteMapper.updateDtoToEntity(requestUpdateDTO, existingUtente);
    final UtenteEntity utenteUpdated = utenteRepositoryService.save(utenteToUpdate);
    log.debug("Service - Utente aggiornato con ID: {}", utenteUpdated.getIdUtente());
    return UtenteMapper.entityToDTO(utenteUpdated);
  }

  @Override
  @Transactional(readOnly = true)
  public UtenteResponseDTO getById(Integer idUtente) {
    log.debug("Service - Recupero utente con ID: {}", idUtente);
    final UtenteEntity utenteEntity = utenteRepositoryService.getById(idUtente);
    log.debug("Service - Utente recuperato: {}", utenteEntity);
    return UtenteMapper.entityToDTO(utenteEntity);
  }

  @Override
  @Transactional
  public void deleteById(Integer idUtente) {
    log.debug("Service - Cancellazione utente con ID: {}", idUtente);
    utenteRepositoryService.deleteById(idUtente);
    log.debug("Service - Utente con ID: {} cancellato", idUtente);
  }
}
