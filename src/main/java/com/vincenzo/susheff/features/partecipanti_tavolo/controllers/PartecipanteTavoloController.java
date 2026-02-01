package com.vincenzo.susheff.features.partecipanti_tavolo.controllers;

import com.vincenzo.susheff.common.response.ApiResponse;
import com.vincenzo.susheff.common.response.Metadata;
import com.vincenzo.susheff.features.partecipanti_tavolo.dto.PartecipanteTavoloResponseDTO;
import com.vincenzo.susheff.features.partecipanti_tavolo.services.PartecipanteTavoloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Slf4j
@Tag(name = "Partecipanti Tavoli API", description = "Gestione delle partecipazioni ai tavoli")
@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PartecipanteTavoloController {
  private final PartecipanteTavoloService partecipanteTavoloService;

  @Operation(summary = "Aggiungi un partecipante a un tavolo")
  @PostMapping("/tavoli/{codiceTavolo}/partecipanti/{nomeUtente}")
  public ResponseEntity<ApiResponse<PartecipanteTavoloResponseDTO>> partecipaTavolo(
    @PathVariable String codiceTavolo,
    @PathVariable String nomeUtente
  ) {
    PartecipanteTavoloResponseDTO newPartecipante = partecipanteTavoloService.partecipaTavolo(codiceTavolo, nomeUtente);
    ApiResponse<PartecipanteTavoloResponseDTO> response = new ApiResponse<>(newPartecipante, new Metadata());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @Operation(summary = "Rimuovi un partecipante da un tavolo")
  @DeleteMapping("/tavoli/{codiceTavolo}/partecipanti/{nomeUtente}")
  public ResponseEntity<ApiResponse<Void>> abbandonaTavolo(
    @PathVariable String codiceTavolo,
    @PathVariable String nomeUtente
  ) {
    partecipanteTavoloService.abbandonaTavolo(codiceTavolo, nomeUtente);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(null, new Metadata()));
  }

}
