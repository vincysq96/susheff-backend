package com.vincenzo.susheff.features.ordini_tavolo.services;

import com.vincenzo.susheff.features.ordini_tavolo.dto.OrdineTavoloResponseDTO;
import com.vincenzo.susheff.features.tavoli.entities.TavoloEntity;

public interface OrdineTavoloService {
  OrdineTavoloResponseDTO getOrdineTavolo(Integer idOrdineTavolo);
  OrdineTavoloResponseDTO getOrdineCorrentePerTavolo(String codiceTavolo);
  void creaOrdineTavolo(TavoloEntity tavoloEntity);
  OrdineTavoloResponseDTO inviaOrdineTavolo(Integer idOrdineTavolo);
  OrdineTavoloResponseDTO chiudiOrdineTavolo(Integer idOrdineTavolo);

}
