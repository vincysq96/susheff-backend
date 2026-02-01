package com.vincenzo.susheff.features.ordini_tavolo.repositories;

import com.vincenzo.susheff.common.data.enumerations.StatoOrdineEnum;
import com.vincenzo.susheff.features.ordini_tavolo.entities.OrdineTavoloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdineTavoloRepository extends JpaRepository<OrdineTavoloEntity, Integer> {
  Optional<OrdineTavoloEntity> findTopByTavolo_CodiceTavoloAndStatusOrdineOrderByDataOrdineDesc(String codiceTavolo, StatoOrdineEnum statusOrdine);


}