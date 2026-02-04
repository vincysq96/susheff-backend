package com.vincenzo.susheff.features.ordini_utente.mappers;

import com.vincenzo.susheff.features.ordini_tavolo.entities.OrdineTavoloEntity;
import com.vincenzo.susheff.features.ordini_utente.dto.OrdineUtenteRequestCreateDTO;
import com.vincenzo.susheff.features.ordini_utente.dto.OrdineUtenteRequestUpdateDTO;
import com.vincenzo.susheff.features.ordini_utente.dto.OrdineUtenteResponseDTO;
import com.vincenzo.susheff.features.ordini_utente.entities.OrdineUtenteEntity;
import com.vincenzo.susheff.features.utenti.entities.UtenteEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrdineUtenteMapper {
  public static OrdineUtenteResponseDTO entityToDTO(OrdineUtenteEntity entity) {
    return new OrdineUtenteResponseDTO(
      entity.getIdOrdineUtente(),
      entity.getOrdineTavolo().getIdOrdineTavolo(),
      entity.getUtente().getIdUtente(),
      entity.getUtente().getNomeUtente(),
      entity.getCodicePiatto(),
      entity.getQuantita(),
      entity.getNotes()
    );
  }

  public static OrdineUtenteEntity createNewEntity(OrdineUtenteRequestCreateDTO requestCreate, OrdineTavoloEntity existingTavolo, UtenteEntity existingUtente) {
    return new OrdineUtenteEntity(
      existingTavolo,
      existingUtente,
      requestCreate.codicePiatto(),
      requestCreate.quantita(),
      requestCreate.notes()
    );
  }

  public static OrdineUtenteEntity updateExistingEntity(OrdineUtenteRequestUpdateDTO requestUpdate, OrdineUtenteEntity existingEntity) {
    existingEntity.setCodicePiatto(requestUpdate.codicePiatto());
    existingEntity.setQuantita(requestUpdate.quantita());
    existingEntity.setNotes(requestUpdate.notes());
    return existingEntity;

  }

}
