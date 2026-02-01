package com.vincenzo.susheff.features.tavoli.services;


import com.vincenzo.susheff.features.tavoli.entities.TavoloEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface TavoloRepositoryService {
  TavoloEntity save(TavoloEntity entityToSave);
  boolean existsByCodiceTavolo(String codiceTavolo);
  TavoloEntity getByCodiceTavoloOrThrow(String codiceTavolo);
  List<TavoloEntity> getOpenTables();
  void deleteTavolo (TavoloEntity entityToDelete);
  Integer puliziaTavoli(LocalDateTime dataLimite);

}
