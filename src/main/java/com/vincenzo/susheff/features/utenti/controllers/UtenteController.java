package com.vincenzo.susheff.features.utenti.controllers;

import com.vincenzo.susheff.common.response.ApiResponse;
import com.vincenzo.susheff.common.response.Metadata;
import com.vincenzo.susheff.features.tavoli.dto.TavoloResponseDTO;
import com.vincenzo.susheff.features.utenti.dto.UtenteRequestUpdateDTO;
import com.vincenzo.susheff.features.utenti.dto.UtenteResponseDTO;
import com.vincenzo.susheff.features.utenti.services.UtenteService;
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

import java.util.List;

@Validated
@Slf4j
@Tag(name = "Utenti API", description = "Gestione degli utenti")
@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UtenteController {
  private final UtenteService utenteService;

  @GetMapping("/utenti/{idUtente}")
  @Operation(summary = "Restituisce un utente per ID")
  public ResponseEntity<ApiResponse<UtenteResponseDTO>> getUtenteById(
    @PathVariable Integer idUtente
  ) {
    UtenteResponseDTO utente = utenteService.getById(idUtente);
    return ResponseEntity.ok(new ApiResponse<>(utente, new Metadata()));
  }

  @GetMapping("/utenti/{idUtente}/tavolo-attivo")
  @Operation(summary = "Restituisce il tavolo attivo per un utente")
  public ResponseEntity<ApiResponse<TavoloResponseDTO>> getTavoloAttivoPerUtente(
    @PathVariable Integer idUtente
  ) {
    TavoloResponseDTO tavoloAttivo = utenteService.getTavoloAttivoPerUtente(idUtente);
    return ResponseEntity.ok(new ApiResponse<>(tavoloAttivo, new Metadata()));
  }

  @Operation(summary = "Crea un utente")
  @PostMapping("/utenti/{nomeUtente}")
  //TODO SE ESISTE, L'UTENTE DEVE IMPERSONIFICARSI
  public ResponseEntity<ApiResponse<UtenteResponseDTO>> createUtente(
    @PathVariable String nomeUtente
  ) {
    UtenteResponseDTO createdUtente = utenteService.create(nomeUtente);
    ApiResponse<UtenteResponseDTO> response = new ApiResponse<>(createdUtente, new Metadata());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/utenti/{idUtente}")
  @Operation(summary = "Aggiorna un utente esistente")
  public ResponseEntity<ApiResponse<UtenteResponseDTO>> updateUtenteById(
    @PathVariable Integer idUtente,
    @RequestBody @Valid UtenteRequestUpdateDTO request
  ) {
    UtenteResponseDTO updated = utenteService.updateById(idUtente, request);
    return ResponseEntity.ok(new ApiResponse<>(updated, new Metadata()));
  }

  @Operation(summary = "Cancella un utente esistente")
  @DeleteMapping("/utenti/{idUtente}")
  public ResponseEntity<ApiResponse<List<UtenteResponseDTO>>> deleteUtente(
    @PathVariable Integer idUtente
  ) {
    utenteService.deleteById(idUtente);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(null, new Metadata()));
  }
}
