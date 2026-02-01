package com.vincenzo.susheff.features.ordini_tavolo.controllers;

import com.vincenzo.susheff.common.response.ApiResponse;
import com.vincenzo.susheff.common.response.Metadata;
import com.vincenzo.susheff.features.ordini_tavolo.dto.OrdineTavoloResponseDTO;
import com.vincenzo.susheff.features.ordini_tavolo.services.OrdineTavoloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Slf4j
@Tag(name = "Ordini Tavolo API", description = "Gestione degli ordini del tavolo")
@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrdineTavoloController {
  private final OrdineTavoloService ordineTavoloService;

  @Operation(summary = "Recupera un ordine del tavolo")
  @GetMapping("/ordini-tavolo/{idOrdineTavolo}")
  public ResponseEntity<ApiResponse<OrdineTavoloResponseDTO>> getOrdineTavolo(
    @PathVariable Integer idOrdineTavolo
  ) {
    OrdineTavoloResponseDTO ordineTavolo = ordineTavoloService.getOrdineTavolo(idOrdineTavolo);
    ApiResponse<OrdineTavoloResponseDTO> response = new ApiResponse<>(ordineTavolo, new Metadata());
    return ResponseEntity.ok(response);
  }

  @Operation(summary = "Invia un ordine del tavolo")
  @PostMapping("/ordini-tavolo/{idOrdineTavolo}/invia")
  public ResponseEntity<ApiResponse<OrdineTavoloResponseDTO>> inviaOrdineTavolo(
    @PathVariable Integer idOrdineTavolo
  ) {
    OrdineTavoloResponseDTO ordineInviato = ordineTavoloService.inviaOrdineTavolo(idOrdineTavolo);
    ApiResponse<OrdineTavoloResponseDTO> response = new ApiResponse<>(ordineInviato, new Metadata());
    return ResponseEntity.ok(response);

  }

  @Operation(summary = "Chiudi un ordine del tavolo")
  @PostMapping("/ordini-tavolo/{idOrdineTavolo}/chiusura")
  public ResponseEntity<ApiResponse<OrdineTavoloResponseDTO>> chiudiOrdineTavolo(
    @PathVariable Integer idOrdineTavolo
  ) {
    OrdineTavoloResponseDTO ordineChiuso = ordineTavoloService.chiudiOrdineTavolo(idOrdineTavolo);
    ApiResponse<OrdineTavoloResponseDTO> response = new ApiResponse<>(ordineChiuso, new Metadata());
    return ResponseEntity.ok(response);
  }

  @Operation
  @GetMapping("/ordini-tavolo/{codiceTavolo}/corrente")
  public ResponseEntity<ApiResponse<OrdineTavoloResponseDTO>> getOrdineCorrentePerTavolo(
    @PathVariable String codiceTavolo
  ) {
    OrdineTavoloResponseDTO ordineCorrente = ordineTavoloService.getOrdineCorrentePerTavolo(codiceTavolo);
    ApiResponse<OrdineTavoloResponseDTO> response = new ApiResponse<>(ordineCorrente, new Metadata());
    return ResponseEntity.ok(response);
  }

}
