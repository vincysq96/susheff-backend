package com.vincenzo.susheff.common.response;

import java.time.LocalDateTime;
import java.util.List;

public record Metadata(LocalDateTime timestamp, List<String> errors) {

  public Metadata() {
    this(LocalDateTime.now(), null);
  }

  public Metadata(List<String> errors) {
    this(LocalDateTime.now(), errors == null ? null : List.copyOf(errors));
  }
}