package com.vincenzo.susheff.features.ordini_utente.services;

import com.vincenzo.susheff.features.ordini_utente.entities.OrdineUtenteEntity;

public interface OrdineUtenteRepositoryService {
  OrdineUtenteEntity getByIdOrThrow(Integer idOrdineUtente);
  OrdineUtenteEntity save(OrdineUtenteEntity entityToSave);
  void deleteEntity(OrdineUtenteEntity entityToDelete);
  void deleteById(Integer idOrdineUtente);
}
