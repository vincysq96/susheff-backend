package com.vincenzo.susheff.core.cleanup.schedules;

import com.vincenzo.susheff.features.tavoli.services.TavoloRepositoryService;
import com.vincenzo.susheff.features.utenti.services.UtenteRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CleanupService {
  private final TavoloRepositoryService tavoloRepositoryService;
  private final UtenteRepositoryService utenteRepositoryService;
  private final static long minuti_svecchiamento = 180; // 3 ore

  //@Scheduled(cron = "0 0 * * * *") // ogni ora (aggiungere uno zero al posto del primo asterisco)
  @Transactional
  public void cleanupTavoli() {
    LocalDateTime dataLimite = LocalDateTime.now().minusMinutes(minuti_svecchiamento);
    log.debug("Schedule - Inizio cleanup tavoli creati prima di {}", dataLimite);
    Integer cancellazioni = tavoloRepositoryService.puliziaTavoli(dataLimite);
    log.debug("Schedule - Fine cleanup tavoli. Totale tavoli cancellati: {}", cancellazioni);
  }

  //@Scheduled(cron = "0 0 * * * *") // ogni ora (aggiungere uno zero al posto del primo asterisco)
  @Transactional
  public void cleanupUtenti() {
    LocalDateTime dataLimite = LocalDateTime.now().minusMinutes(minuti_svecchiamento);
    log.debug("Schedule - Inizio cleanup utenti creati da più di {} minuti", minuti_svecchiamento);
    Integer cancellazioni = utenteRepositoryService.puliziaUtenti(dataLimite);
    log.debug("Schedule - Fine cleanup utenti. Totale utenti cancellati: {}", cancellazioni);
  }
}
