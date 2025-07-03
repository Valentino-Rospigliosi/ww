package com.cibertec.edu.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import com.cibertec.edu.dto.TokenDto;

import jakarta.ws.rs.core.HttpHeaders;
import reactor.core.publisher.Mono;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);
	
	private WebClient.Builder builder;
	
	
	public AuthenticationFilter(WebClient.Builder builder) {
		super(Config.class);
		this.builder = builder;
	}

	public static class Config {
		
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			LOGGER.info("Validando peticion a: {}", exchange.getRequest().getPath());
			String token = getToken(exchange.getRequest());
			if(token==null) {
				LOGGER.warn("Token no proporcionado");
				return onError(exchange, HttpStatus.UNAUTHORIZED);
			}
			return validateToken(token)
					.flatMap(isValid -> {
					if(isValid) {
						LOGGER.warn("Token Valido!");
						return chain.filter(exchange);
					}else {
						LOGGER.warn("Token invalido!");
						return onError(exchange, HttpStatus.UNAUTHORIZED);
					}});
		};
	}
	
	private String getToken(ServerHttpRequest request) {
		String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			return null;
		}
		return authHeader.substring(7);
	}
	
	private Mono<Boolean> validateToken(String token){
		LOGGER.warn("TOKEN API GATEWAY: {}",token);
		return (builder.build().post()
				.uri("http://auth-api-rest/v1/auth/validate?token="+token)
				.bodyValue(token)
				.retrieve()
				.bodyToMono(TokenDto.class)
				.map(TokenDto::isValid)
				.onErrorReturn(false));
	}
	
	public Mono<Void> onError(ServerWebExchange exchange, HttpStatus status){
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(status);
		return response.setComplete();
	}
}
