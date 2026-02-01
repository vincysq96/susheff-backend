package com.vincenzo.susheff.features.tavoli.mappers;

import com.vincenzo.susheff.common.data.enumerations.StatoTavoloEnum;
import com.vincenzo.susheff.features.tavoli.dto.TavoloResponseDTO;
import com.vincenzo.susheff.features.tavoli.entities.TavoloEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TavoloMapper {
  public static TavoloResponseDTO entityToDTO(TavoloEntity entity) {
    return new TavoloResponseDTO(
      entity.getIdTavolo(),
      entity.getCodiceTavolo(),
      entity.getDataApertura(),
      entity.getDataChiusura(),
      entity.getStatusTavolo()
    );
  }

  public static TavoloEntity createEntity(String codiceTavolo) {
    return new TavoloEntity(
      codiceTavolo
    );
  }

  public static TavoloEntity closeTable(TavoloEntity entityToUpdate) {
    entityToUpdate.setDataChiusura(LocalDateTime.now());
    entityToUpdate.setStatusTavolo(StatoTavoloEnum.CHIUSO);
    return entityToUpdate;
  }

}
