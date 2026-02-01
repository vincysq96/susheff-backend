package com.vincenzo.susheff.features.utenti.services;

import com.vincenzo.susheff.features.utenti.entities.UtenteEntity;

import java.time.LocalDateTime;

public interface UtenteRepositoryService {
  UtenteEntity save(UtenteEntity entityToSave);
  UtenteEntity getById(Integer idUtente);
  UtenteEntity getByNomeUtenteOrThrow(String nomeUtente);
  void deleteById(Integer idUtente);
  Integer puliziaUtenti(LocalDateTime dataLimite);
  boolean existsByNomeUtente(String nomeUtente);
}
