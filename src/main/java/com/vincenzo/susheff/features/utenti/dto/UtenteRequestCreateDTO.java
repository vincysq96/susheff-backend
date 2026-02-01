package com.vincenzo.susheff.features.utenti.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UtenteRequestCreateDTO(
  @Size(max = 30, message = "Nome utente troppo lungo (max 30 caratteri)")
  @NotBlank(message = "Nome utente mancante")
  String nomeUtente
) {
}
