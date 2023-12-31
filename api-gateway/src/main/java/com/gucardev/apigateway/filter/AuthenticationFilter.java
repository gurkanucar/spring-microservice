package com.gucardev.apigateway.filter;

import com.gucardev.apigateway.dto.AuthResponse;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<Object> {

  @Value("${service-credentials.username}")
  private String username;

  @Value("${service-credentials.pass}")
  private String pass;

  private final WebClient.Builder webClientBuilder;

  public AuthenticationFilter(WebClient.Builder webClientBuilder) {
    this.webClientBuilder = webClientBuilder;
  }

  @Override
  public GatewayFilter apply(Object config) {
    return (exchange, chain) -> {
      String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

      if (authHeader != null && authHeader.startsWith("Bearer ")) {
        String token = authHeader.substring(7);

        String credentials = username + ":" + pass;
        String basicAuthHeader =
            "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        return webClientBuilder
            .build()
            .get()
            .uri(
                uriBuilder ->
                    uriBuilder
                        .scheme("lb")
                        .host("auth-service")
                        .path("/auth-service/auth/validate")
                        .queryParam("token", token)
                        .build())
            .retrieve()
            .toEntity(AuthResponse.class)
            .flatMap(
                authResponse -> {
                  if (authResponse.getStatusCode().is2xxSuccessful()) {
                    AuthResponse authObj = authResponse.getBody();
                    exchange
                        .getRequest()
                        .mutate()
                        .header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
                        .header("X-Authenticated-User", authObj.username())
                        .header("X-Authenticated-Roles", String.join(",", authObj.roles()))
                        .build();
                    return chain.filter(exchange);
                  } else {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                  }
                })
            .onErrorResume(throwable -> handleAuthenticationError(exchange));
      } else {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
      }
    };
  }

  private Mono<Void> handleAuthenticationError(ServerWebExchange exchange) {
    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
    return exchange.getResponse().setComplete();
  }
}
