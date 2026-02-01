package com.vincenzo.susheff.features.ordini_utente.repositories;

import com.vincenzo.susheff.features.ordini_utente.entities.OrdineUtenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdineUtenteRepository extends JpaRepository<OrdineUtenteEntity, Integer> {
}