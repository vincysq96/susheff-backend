package com.vincenzo.susheff.features.utenti.services;

import com.vincenzo.susheff.features.tavoli.dto.TavoloResponseDTO;
import com.vincenzo.susheff.features.utenti.dto.UtenteRequestUpdateDTO;
import com.vincenzo.susheff.features.utenti.dto.UtenteResponseDTO;

public interface UtenteService {
  UtenteResponseDTO create(String nomeUtente);
  UtenteResponseDTO updateById(Integer idUtente, UtenteRequestUpdateDTO requestUpdateDTO);
  UtenteResponseDTO getById(Integer idUtente);
  void deleteById(Integer idUtente);
  TavoloResponseDTO getTavoloAttivoPerUtente(Integer idUtente);
}
