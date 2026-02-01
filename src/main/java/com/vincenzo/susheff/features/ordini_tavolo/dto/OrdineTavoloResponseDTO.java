package com.vincenzo.susheff.features.ordini_tavolo.dto;

import com.vincenzo.susheff.common.data.enumerations.StatoOrdineEnum;
import com.vincenzo.susheff.features.ordini_utente.dto.OrdineUtenteResponseDTO;

import java.time.LocalDateTime;
import java.util.Set;

public record OrdineTavoloResponseDTO(
  Integer idOrdineTavolo,
  Integer idTavolo,
  StatoOrdineEnum statusOrdine,
  Integer roundOrdinazione,
  LocalDateTime dataOrdine,
  Set<OrdineUtenteResponseDTO> ordiniUtente
) {
}
