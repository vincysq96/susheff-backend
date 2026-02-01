package com.vincenzo.susheff.features.tavoli.repositories;

import com.vincenzo.susheff.common.data.enumerations.StatoTavoloEnum;
import com.vincenzo.susheff.features.tavoli.entities.TavoloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TavoloRepository extends JpaRepository<TavoloEntity, Integer> {
  List<TavoloEntity> findByAndStatusTavoloAndDataAperturaAfter(StatoTavoloEnum statusTavolo, LocalDateTime dataLimite);
  List<TavoloEntity> findByDataAperturaBefore(LocalDateTime dataLimite);
  Optional<TavoloEntity> findByCodiceTavolo(String codiceTavolo);
  boolean existsByCodiceTavolo(String codiceTavolo);
}