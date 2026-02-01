package com.vincenzo.susheff.features.partecipanti_tavolo.services;

import com.vincenzo.susheff.common.data.enumerations.StatoTavoloEnum;
import com.vincenzo.susheff.features.partecipanti_tavolo.dto.PartecipanteTavoloResponseDTO;
import com.vincenzo.susheff.features.partecipanti_tavolo.entities.PartecipanteTavoloEntity;
import com.vincenzo.susheff.features.partecipanti_tavolo.mappers.PartecipanteTavoloMapper;
import com.vincenzo.susheff.features.tavoli.entities.TavoloEntity;
import com.vincenzo.susheff.features.tavoli.services.TavoloRepositoryService;
import com.vincenzo.susheff.features.utenti.entities.UtenteEntity;
import com.vincenzo.susheff.features.utenti.services.UtenteRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
@Service
public class PartecipanteTavoloServiceImpl implements PartecipanteTavoloService {
  private final UtenteRepositoryService utenteRepositoryService;
  private final TavoloRepositoryService tavoloRepositoryService;
  private final PartecipanteTavoloRepositoryService partecipanteTavoloRepositoryService;

  @Override
  @Transactional
  public PartecipanteTavoloResponseDTO partecipaTavolo(String codiceTavolo, String nomeUtente) {
    log.debug("Service - Utente con codice: {} si unisce al tavolo con codice: {}", nomeUtente, codiceTavolo);
    TavoloEntity existingTavolo = tavoloRepositoryService.getByCodiceTavoloOrThrow(codiceTavolo);
    UtenteEntity existingUtente = utenteRepositoryService.getByNomeUtenteOrThrow(nomeUtente);
    checkTavoloAperto(existingTavolo);
    boolean partecipanteAlTavolo = partecipanteTavoloRepositoryService.existsByUtenteAndTavolo(existingUtente, existingTavolo);
    if (partecipanteAlTavolo) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Utente già presente al tavolo");
    } else {
      PartecipanteTavoloEntity partecipante = new PartecipanteTavoloEntity(existingUtente, existingTavolo);
      final PartecipanteTavoloEntity savedPartecipante = partecipanteTavoloRepositoryService.save(partecipante);
      log.debug("Service - Utente con codice: {} si è unito al tavolo con codice: {}", nomeUtente, codiceTavolo);
      return PartecipanteTavoloMapper.entityToDTO(savedPartecipante);
    }
  }

  @Override
  @Transactional
  public void abbandonaTavolo(String codiceTavolo, String nomeUtente) {
    log.debug("Service - Utente: {} lascia il tavolo: {}", nomeUtente, codiceTavolo);
    TavoloEntity existingTavolo = tavoloRepositoryService.getByCodiceTavoloOrThrow(codiceTavolo);
    UtenteEntity existingUtente = utenteRepositoryService.getByNomeUtenteOrThrow(nomeUtente);
    boolean partecipanteAlTavolo = partecipanteTavoloRepositoryService.existsByUtenteAndTavolo(existingUtente, existingTavolo);
    if (!partecipanteAlTavolo) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Utente non presente al tavolo");
    } else {
      partecipanteTavoloRepositoryService.deleteByUtenteAndTavolo(existingUtente, existingTavolo);
      log.debug("Service - Utente: {} ha lasciato il tavolo: {}", nomeUtente, codiceTavolo);
    }
  }

  private void checkTavoloAperto(TavoloEntity existingTavolo) {
    if (existingTavolo.getStatusTavolo() != StatoTavoloEnum.APERTO) {
      log.debug("Service - Impossibile unirsi al tavolo perché non è aperto");
      throw new ResponseStatusException(
        HttpStatus.CONFLICT,
        "Impossibile unirsi al tavolo perché il tavolo non è aperto."
      );
    }
  }

}
