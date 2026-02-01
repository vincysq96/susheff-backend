package com.vincenzo.susheff.features.ordini_utente.services;

import com.vincenzo.susheff.features.ordini_utente.dto.OrdineUtenteRequestCreateDTO;
import com.vincenzo.susheff.features.ordini_utente.dto.OrdineUtenteRequestUpdateDTO;
import com.vincenzo.susheff.features.ordini_utente.dto.OrdineUtenteResponseDTO;

public interface OrdineUtenteService {
  OrdineUtenteResponseDTO getOrdineUtente(Integer idOrdineUtente);
  OrdineUtenteResponseDTO creaOrdineUtente(Integer idOrdineTavolo, Integer idOrdineUtente, OrdineUtenteRequestCreateDTO requestCreate);
  OrdineUtenteResponseDTO modificaOrdineUtente(Integer idOrdineUtente, OrdineUtenteRequestUpdateDTO requestUpdate);
  void eliminaOrdineUtente(Integer idOrdineUtente);
}
