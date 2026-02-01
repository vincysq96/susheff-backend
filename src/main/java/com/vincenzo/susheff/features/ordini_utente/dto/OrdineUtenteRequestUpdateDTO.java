package com.vincenzo.susheff.features.ordini_utente.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OrdineUtenteRequestUpdateDTO(
  @Size(max = 15, message = "Codice piatto troppo lungo (max 15 caratteri)")
  @NotBlank(message = "Nome piatto mancante")
  String codicePiatto,

  Integer quantita,

  @Size(max = 50, message = "Note piatto troppo lunghe (max 50 caratteri)")
  String notes
) {
}
