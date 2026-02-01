package com.vincenzo.susheff.features.utenti.dto;

import java.time.LocalDateTime;

public record UtenteResponseDTO(
  Integer idUtente,
  String nomeUtente,
  LocalDateTime dataCreazione
) {
}
