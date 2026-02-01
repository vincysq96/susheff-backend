package com.vincenzo.susheff.features.tavoli.services;

import com.vincenzo.susheff.features.tavoli.dto.TavoloResponseDTO;

import java.util.List;

public interface TavoloService {
  TavoloResponseDTO openTable(String codiceTavolo);
  TavoloResponseDTO closeTable(String codiceTavolo);
  List<TavoloResponseDTO> getOpenTables();
  void deleteByCodice(String codiceTavolo);

}
