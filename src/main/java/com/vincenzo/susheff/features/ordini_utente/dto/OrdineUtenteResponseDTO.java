package com.vincenzo.susheff.features.ordini_utente.dto;

public record OrdineUtenteResponseDTO(
   Integer idOrdineUtente,
   Integer idOrdineTavolo,
   Integer idUtente,
   String codicePiatto,
   Integer quantita,
   String notes
) {
}
