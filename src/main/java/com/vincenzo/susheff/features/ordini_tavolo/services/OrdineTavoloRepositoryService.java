package com.vincenzo.susheff.features.ordini_tavolo.services;


import com.vincenzo.susheff.features.ordini_tavolo.entities.OrdineTavoloEntity;

public interface OrdineTavoloRepositoryService {
  OrdineTavoloEntity getByIdOrThrow(Integer idOrdineTavolo);
  OrdineTavoloEntity save(OrdineTavoloEntity entityToSave);
  OrdineTavoloEntity getUltimoOrdineTavoloInCorsoOrThrow(String codiceTavolo);
}
