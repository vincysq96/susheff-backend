package com.vincenzo.susheff.features.tavoli.dto;

import com.vincenzo.susheff.common.data.enumerations.StatoTavoloEnum;

import java.time.LocalDateTime;

public record TavoloResponseDTO(
  Integer idTavolo,
  String codiceTavolo,
  LocalDateTime dataApertura,
  LocalDateTime dataChiusura,
  StatoTavoloEnum statusTavolo
) {
}
