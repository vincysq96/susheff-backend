package com.vincenzo.susheff.features.tavoli.controllers;

import com.vincenzo.susheff.common.response.ApiResponse;
import com.vincenzo.susheff.common.response.Metadata;
import com.vincenzo.susheff.features.ordini_tavolo.dto.OrdineTavoloResponseDTO;
import com.vincenzo.susheff.features.ordini_tavolo.services.OrdineTavoloService;
import com.vincenzo.susheff.features.tavoli.dto.TavoloResponseDTO;
import com.vincenzo.susheff.features.tavoli.services.TavoloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Slf4j
@Tag(name = "Tavoli API", description = "Gestione dei tavoli")
@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TavoloController {
  private final TavoloService tavoloService;
  private final OrdineTavoloService ordineTavoloService;

  @Operation(summary = "Apre un tavolo")
  @PostMapping("/tavoli/{codiceTavolo}")
  public ResponseEntity<ApiResponse<TavoloResponseDTO>> openTavolo(
    @PathVariable String codiceTavolo
  ) {
    TavoloResponseDTO createdTavolo = tavoloService.openTable(codiceTavolo);
    ApiResponse<TavoloResponseDTO> response = new ApiResponse<>(createdTavolo, new Metadata());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/tavoli/{codiceTavolo}")
  @Operation(summary = "Chiude un tavolo esistente")
  public ResponseEntity<ApiResponse<TavoloResponseDTO>> closeTavolo(
    @PathVariable String codiceTavolo
  ) {
    TavoloResponseDTO updated = tavoloService.closeTable(codiceTavolo);
    return ResponseEntity.ok(new ApiResponse<>(updated, new Metadata()));
  }

  @Operation(summary = "Cancella un tavolo esistente")
  @DeleteMapping("/tavoli/{codiceTavolo}")
  public ResponseEntity<ApiResponse<List<Void>>> deleteTavolo(
    @PathVariable String codiceTavolo
  ) {
    tavoloService.deleteByCodice(codiceTavolo);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(null, new Metadata()));
  }

  @Operation(summary = "Restituisce tutti i tavoli aperti")
  @GetMapping("/tavoli/open")
  public ResponseEntity<ApiResponse<List<TavoloResponseDTO>>> getOpenTavoli() {
    List<TavoloResponseDTO> openTavoli = tavoloService.getOpenTables();
    return ResponseEntity.ok(new ApiResponse<>(openTavoli, new Metadata()));
  }

  @GetMapping("/tavoli/{codiceTavolo}/ordine-corrente")
  @Operation(summary = "Restituisce l'ordine corrente per un tavolo")
  public ResponseEntity<ApiResponse<OrdineTavoloResponseDTO>> getOrdineCorrenteTavolo(
    @PathVariable String codiceTavolo
  ) {
    OrdineTavoloResponseDTO ordineCorrente = ordineTavoloService.getOrdineCorrentePerTavolo(codiceTavolo);
    return ResponseEntity.ok(new ApiResponse<>(ordineCorrente, new Metadata()));
  }

}
