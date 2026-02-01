package com.vincenzo.susheff.features.utenti.repositories;

import com.vincenzo.susheff.features.utenti.entities.UtenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UtenteRepository extends JpaRepository<UtenteEntity, Integer> {
  List<UtenteEntity> findByDataCreazioneBefore(LocalDateTime dataLimite);
  Optional<UtenteEntity> findByNomeUtente(String nomeUtente);
  boolean existsByNomeUtente(String nomeUtente);

}