package com.vincenzo.susheff.features.ordini_utente.dto;

public record OrdineUtenteResponseDTO(
   Integer idOrdineUtente,
   Integer idOrdineTavolo,
   Integer idUtente,
   String nomeUtente,
   String codicePiatto,
   Integer quantita,
   String notes
) {
}
