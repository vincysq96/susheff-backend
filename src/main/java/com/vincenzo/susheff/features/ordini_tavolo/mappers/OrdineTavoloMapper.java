package com.vincenzo.susheff.features.ordini_tavolo.mappers;

import com.vincenzo.susheff.common.data.enumerations.StatoOrdineEnum;
import com.vincenzo.susheff.features.ordini_tavolo.dto.OrdineTavoloResponseDTO;
import com.vincenzo.susheff.features.ordini_tavolo.entities.OrdineTavoloEntity;
import com.vincenzo.susheff.features.ordini_utente.mappers.OrdineUtenteMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrdineTavoloMapper {


  public static OrdineTavoloResponseDTO entityToDTO(OrdineTavoloEntity ordineTavoloEntity) {
    return new OrdineTavoloResponseDTO(
        ordineTavoloEntity.getIdOrdineTavolo(),
        ordineTavoloEntity.getTavolo().getIdTavolo(),
        ordineTavoloEntity.getStatusOrdine(),
        ordineTavoloEntity.getRoundOrdinazione(),
        ordineTavoloEntity.getDataOrdine(),
        ordineTavoloEntity.getOrdiniUtente().stream()
          .map(OrdineUtenteMapper::entityToDTO)
          .collect(java.util.stream.Collectors.toSet())
    );
  }

  public static OrdineTavoloEntity getProssimoOrdineTavolo(OrdineTavoloEntity existingOrdineTavolo) {
    return new OrdineTavoloEntity(
      existingOrdineTavolo.getTavolo(),
      existingOrdineTavolo.getRoundOrdinazione() + 1
    );
  }

  public static OrdineTavoloEntity chiudiOrdineTavolo(OrdineTavoloEntity existingOrdineTavolo) {
    existingOrdineTavolo.setStatusOrdine(StatoOrdineEnum.CHIUSO);
    return existingOrdineTavolo;
  }
}
