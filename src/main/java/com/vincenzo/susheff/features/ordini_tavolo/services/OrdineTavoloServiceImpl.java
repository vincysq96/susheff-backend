package com.vincenzo.susheff.features.ordini_tavolo.services;

import com.vincenzo.susheff.features.ordini_tavolo.dto.OrdineTavoloResponseDTO;
import com.vincenzo.susheff.features.ordini_tavolo.entities.OrdineTavoloEntity;
import com.vincenzo.susheff.features.ordini_tavolo.mappers.OrdineTavoloMapper;
import com.vincenzo.susheff.features.tavoli.entities.TavoloEntity;
import com.vincenzo.susheff.features.tavoli.services.TavoloRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrdineTavoloServiceImpl implements OrdineTavoloService {

  private final OrdineTavoloRepositoryService ordineTavoloRepositoryService;
  private final TavoloRepositoryService tavoloRepositoryService;

  @Override
  @Transactional(readOnly = true)
  public OrdineTavoloResponseDTO getOrdineTavolo(Integer idOrdineTavolo) {
    log.debug("Service - Recupero ordine tavolo con id: {}", idOrdineTavolo);
    final OrdineTavoloEntity ordineTavoloEntity = ordineTavoloRepositoryService.getByIdOrThrow(idOrdineTavolo);
    log.debug("Service - Ordine tavolo con id: {} recuperato", idOrdineTavolo);
    return OrdineTavoloMapper.entityToDTO(ordineTavoloEntity);
  }

  @Override
  @Transactional(readOnly = true)
  public OrdineTavoloResponseDTO getOrdineCorrentePerTavolo(String codiceTavolo) {
    log.debug("Service - Recupero ordine corrente per tavolo con codice: {}", codiceTavolo);
    //get ultimo ordine tavolo con statusOrdine = NUOVO con tavolo.codice_Tavolo = codiceTavolo
    OrdineTavoloEntity ordineTavoloCorrente = ordineTavoloRepositoryService.getUltimoOrdineTavoloInCorsoOrThrow(codiceTavolo);
    log.debug("Service - Ordine corrente per tavolo con codice: {} recuperato", codiceTavolo);
    return OrdineTavoloMapper.entityToDTO(ordineTavoloCorrente);
  }

  @Override
  @Transactional
  public OrdineTavoloResponseDTO inviaOrdineTavolo(Integer idOrdineTavolo) {
    final OrdineTavoloEntity ordineChiuso = chiudiOrdine(idOrdineTavolo);
    log.debug("Service - Creazione nuovo ordine tavolo per tavolo id: {}", ordineChiuso.getTavolo().getIdTavolo());
    OrdineTavoloEntity nuovoOrdineTavolo = OrdineTavoloMapper.getProssimoOrdineTavolo(ordineChiuso);
    final OrdineTavoloEntity savedNewOrdine = ordineTavoloRepositoryService.save(nuovoOrdineTavolo);
    log.debug("Service - Nuovo ordine tavolo creato con id: {}", savedNewOrdine.getIdOrdineTavolo());
    return OrdineTavoloMapper.entityToDTO(savedNewOrdine);
  }

  @Override
  public void creaOrdineTavolo(TavoloEntity tavoloEntity) {
    log.debug("Service - Creazione ordine tavolo per tavolo con codice: {}", tavoloEntity.getCodiceTavolo());
    final OrdineTavoloEntity newOrdineTavolo = new OrdineTavoloEntity(tavoloEntity, 1);
    final OrdineTavoloEntity savedOrdine = ordineTavoloRepositoryService.save(newOrdineTavolo);
    log.debug("Service - Ordine tavolo creato con id: {}", savedOrdine.getIdOrdineTavolo());
    OrdineTavoloMapper.entityToDTO(savedOrdine);
  }

  @Override
  @Transactional
  public OrdineTavoloResponseDTO chiudiOrdineTavolo(Integer idOrdineTavolo) {
    final OrdineTavoloEntity ordineChiuso = chiudiOrdine(idOrdineTavolo);
    return OrdineTavoloMapper.entityToDTO(ordineChiuso);
  }

  private OrdineTavoloEntity chiudiOrdine(Integer idOrdineTavolo) {
    log.debug("Service - Chiusura ordine tavolo con id: {}", idOrdineTavolo);
    OrdineTavoloEntity ordine = ordineTavoloRepositoryService.getByIdOrThrow(idOrdineTavolo);
    OrdineTavoloEntity ordineChiuso = OrdineTavoloMapper.chiudiOrdineTavolo(ordine);
    log.debug("Service - Chiusura ordine tavolo con id: {} completata", idOrdineTavolo);
    return ordineTavoloRepositoryService.save(ordineChiuso);
  }

}
