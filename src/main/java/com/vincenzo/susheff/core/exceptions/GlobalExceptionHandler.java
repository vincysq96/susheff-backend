package com.vincenzo.susheff.core.exceptions;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.vincenzo.susheff.common.response.ApiResponse;
import com.vincenzo.susheff.common.response.Metadata;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  // Errori di validazione dei DTO (es. @Valid su @RequestBody)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Void>> handleValidationErrors(MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getField() + ": " + error.getDefaultMessage()).toList();

    Metadata metadata = new Metadata(errors);
    log.error("Errore di validazione: {}", errors);
    return ResponseEntity.badRequest().body(new ApiResponse<>(null, metadata));
  }

  // Se la causa è un campo nelle request non riconosciuto
  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
    Throwable cause = ex.getCause();
    String error;

    if (cause instanceof UnrecognizedPropertyException upe) {
      String fieldName = upe.getPropertyName();
      error = "Campo non riconosciuto nel body JSON: \"" + fieldName + "\".";
      log.error("Errore di deserializzazione - proprietà non riconosciuta: {}", error);
    } else {
      error = "Errore nel body della richiesta: " + ex.getMessage();
      log.error("Errore di deserializzazione: {}", error);
    }

    Metadata metadata = new Metadata(List.of(error));
    return ResponseEntity.badRequest().body(new ApiResponse<>(null, metadata));
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ApiResponse<Void>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
    String message = "Violazione di vincolo nel database.";
    log.error("Violazione di integrità nei dati: {}", ex.getMessage(), ex);
    Metadata metadata = new Metadata(List.of(message));
    return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(null, metadata));
  }


  // Violazioni di vincoli (es. @Min su @PathVariable o @RequestParam)
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ApiResponse<Void>> handleConstraintViolation(ConstraintViolationException ex) {
    List<String> errors = ex.getConstraintViolations().stream().map(violation -> violation.getPropertyPath() + ": " + violation.getMessage()).toList();
    Metadata metadata = new Metadata(errors);
    log.error("Errore di vincolo: {}", errors);
    return ResponseEntity.badRequest().body(new ApiResponse<>(null, metadata));
  }

  @ExceptionHandler(MissingRequestHeaderException.class)
  public ResponseEntity<ApiResponse<Void>> handleMissingRequestHeader(MissingRequestHeaderException ex) {
    String error = "Header mancante: " + ex.getHeaderName();
    Metadata metadata = new Metadata(List.of(error));
    log.error("Errore header mancante: {}", error);
    return ResponseEntity.badRequest().body(new ApiResponse<>(null, metadata));
  }

  // Intercetta ResponseStatusException (es. 404 Not Found da lanciare manualmente)
  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ApiResponse<Object>> handleResponseStatusException(ResponseStatusException ex) {
    String message = Optional.ofNullable(ex.getReason()).orElse("Errore generico");
    Metadata metadata = new Metadata(List.of(message));
    log.error("Errore di stato della risposta: {}", message);
    return new ResponseEntity<>(new ApiResponse<>(null, metadata), ex.getStatusCode());
  }

  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<ApiResponse<Void>> handleIllegalStateException(IllegalStateException ex) {
    String message =
        Optional.ofNullable(ex.getMessage())
        .orElse("Operazione non consentita nello stato attuale");

    log.error("Operazione non consentita: {}", message);

    Metadata metadata = new Metadata(List.of(message));

    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(new ApiResponse<>(null, metadata));
  }


  // Catch-all per tutte le altre eccezioni non gestite
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {
    log.error("Si è verificato un errore non gestito", ex);
    Metadata metadata = new Metadata(List.of("Si è verificato un errore inaspettato"));
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, metadata));
  }
}
