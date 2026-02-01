package com.vincenzo.susheff.features.utenti.mappers;

import com.vincenzo.susheff.features.utenti.dto.UtenteRequestCreateDTO;
import com.vincenzo.susheff.features.utenti.dto.UtenteRequestUpdateDTO;
import com.vincenzo.susheff.features.utenti.dto.UtenteResponseDTO;
import com.vincenzo.susheff.features.utenti.entities.UtenteEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UtenteMapper {
  public static UtenteResponseDTO entityToDTO(UtenteEntity entity) {
    return new UtenteResponseDTO(
      entity.getIdUtente(),
      entity.getNomeUtente(),
      entity.getDataCreazione()
    );
  }

  public static UtenteEntity createDtoToEntity(UtenteRequestCreateDTO requestCreateDTO) {
    return new UtenteEntity(
      requestCreateDTO.nomeUtente()
    );
  }

  public static UtenteEntity updateDtoToEntity(UtenteRequestUpdateDTO requestUpdateDTO, UtenteEntity entityToUpdate) {
    entityToUpdate.setNomeUtente(requestUpdateDTO.nomeUtente());
    return entityToUpdate;
  }
}
