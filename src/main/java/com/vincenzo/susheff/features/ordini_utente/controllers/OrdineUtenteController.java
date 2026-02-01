package com.vincenzo.susheff.features.ordini_utente.controllers;

import com.vincenzo.susheff.common.response.ApiResponse;
import com.vincenzo.susheff.common.response.Metadata;
import com.vincenzo.susheff.features.ordini_utente.dto.OrdineUtenteRequestCreateDTO;
import com.vincenzo.susheff.features.ordini_utente.dto.OrdineUtenteRequestUpdateDTO;
import com.vincenzo.susheff.features.ordini_utente.dto.OrdineUtenteResponseDTO;
import com.vincenzo.susheff.features.ordini_utente.services.OrdineUtenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Slf4j
@Tag(name = "Ordini Utente API", description = "Gestione degli ordini degli utenti")
@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrdineUtenteController {
  private final OrdineUtenteService ordineUtenteService;

  @Operation(summary = "Crea un ordine di un utente")
  @PostMapping("/ordini-utente/tavolo/{idOrdineTavolo}/utente/{idOrdineUtente}")
  public ResponseEntity<ApiResponse<OrdineUtenteResponseDTO>> creaOrdineUtente(
    @Valid @RequestBody OrdineUtenteRequestCreateDTO request,
    @PathVariable Integer idOrdineTavolo,
    @PathVariable Integer idOrdineUtente
  ) {
    OrdineUtenteResponseDTO newOrdineUtente = ordineUtenteService.creaOrdineUtente(idOrdineTavolo, idOrdineUtente, request);
    ApiResponse<OrdineUtenteResponseDTO> response = new ApiResponse<>(newOrdineUtente, new Metadata());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @Operation(summary = "Recupera un ordine utente")
  @GetMapping("/ordini-utente/{idOrdineUtente}")
  public ResponseEntity<ApiResponse<OrdineUtenteResponseDTO>> getOrdiniUtente(
    @PathVariable Integer idOrdineUtente
  ){
    OrdineUtenteResponseDTO ordineUtente = ordineUtenteService.getOrdineUtente(idOrdineUtente);
    ApiResponse<OrdineUtenteResponseDTO> response = new ApiResponse<>(ordineUtente, new Metadata());
    return ResponseEntity.ok(response);
  }

  @Operation(summary = "Modifica un ordine utente")
  @PutMapping("/ordini-utente/{idOrdineUtente}")
  public ResponseEntity<ApiResponse<OrdineUtenteResponseDTO>> modificaOrdineUtente(
    @PathVariable Integer idOrdineUtente,
    @Valid @RequestBody OrdineUtenteRequestUpdateDTO requestUpdate){
    OrdineUtenteResponseDTO updatedOrdineUtente = ordineUtenteService.modificaOrdineUtente(idOrdineUtente, requestUpdate);
    ApiResponse<OrdineUtenteResponseDTO> response = new ApiResponse<>(updatedOrdineUtente, new Metadata());
    return ResponseEntity.ok(response);
  }

  @Operation(summary = "Elimina un ordine utente")
  @DeleteMapping("/ordini-utente/{idOrdineUtente}")
  public ResponseEntity<ApiResponse<Void>> eliminaOrdineUtente(
    @PathVariable Integer idOrdineUtente){
    ordineUtenteService.eliminaOrdineUtente(idOrdineUtente);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(null, new Metadata()));
  }


}
