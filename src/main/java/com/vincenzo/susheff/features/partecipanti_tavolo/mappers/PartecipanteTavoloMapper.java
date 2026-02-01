package com.vincenzo.susheff.features.partecipanti_tavolo.mappers;

import com.vincenzo.susheff.features.partecipanti_tavolo.dto.PartecipanteTavoloResponseDTO;
import com.vincenzo.susheff.features.partecipanti_tavolo.entities.PartecipanteTavoloEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PartecipanteTavoloMapper {
  public static PartecipanteTavoloResponseDTO entityToDTO(PartecipanteTavoloEntity entity) {
    return new PartecipanteTavoloResponseDTO(
      entity.getTavolo().getCodiceTavolo(),
      entity.getUtente().getNomeUtente()
    );
  }

}
