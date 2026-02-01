package com.vincenzo.susheff.core.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {

    long start = System.currentTimeMillis();

    String method = request.getMethod();
    String uri = request.getRequestURI();

    log.info(">>> [{}] {} - Start", method, uri);

    filterChain.doFilter(request, response);
    long duration = System.currentTimeMillis() - start;

    log.info("[{}] {} - Completed in {} ms", method, uri, duration);
  }
}