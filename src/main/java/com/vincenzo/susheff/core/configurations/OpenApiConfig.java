package com.vincenzo.susheff.core.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Value("${swagger.server.url}")
  private String swaggerServerUrl;

  @Value("${swagger.server.desc}")
  private String swaggerServerDesc;

  @Bean
  public OpenAPI customOpenAPIWithoutJWT() {
    return new OpenAPI()
        .addServersItem(
            new Server()
                .url(swaggerServerUrl)
                .description(swaggerServerDesc)
        );
  }
}
