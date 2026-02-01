package com.vincenzo.susheff.features.partecipanti_tavolo.services;

import com.vincenzo.susheff.features.partecipanti_tavolo.dto.PartecipanteTavoloResponseDTO;

public interface PartecipanteTavoloService {
  PartecipanteTavoloResponseDTO partecipaTavolo(String codiceTavolo, String nomeUtente);
  void abbandonaTavolo(String codiceTavolo, String nomeUtente);
}
