package gateway_server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

	@Bean
	SecurityWebFilterChain secureRequests(ServerHttpSecurity httpWebSecurity) {

		httpWebSecurity.authorizeExchange(authExchange -> authExchange
				// Allow all GET requests
				.pathMatchers(HttpMethod.GET).permitAll()
				// Require authentication for specific APIs
				.pathMatchers("/accounts/eazybank/accounts/**").hasRole("ACCOUNTS")
				.pathMatchers("/cards/cards/**").hasRole("CARDS").pathMatchers("/loans/loans/**")
				.hasRole("LOANS"))
				// Disable CSRF for stateless APIs
				.csrf(ServerHttpSecurity.CsrfSpec::disable)
				// Enable JWT-based authentication
				.oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
						.jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(extractGrantedAuthorities())));

		return httpWebSecurity.build();
	}

	private Converter<Jwt, Mono<AbstractAuthenticationToken>> extractGrantedAuthorities() {
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeyCloakResourceConverter());
		return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
	}
}
