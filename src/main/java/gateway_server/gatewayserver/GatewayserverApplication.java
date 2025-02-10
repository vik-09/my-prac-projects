package gateway_server.gatewayserver;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	RouteLocator eazyBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		Set<String> httpStatusCodes = new HashSet<String>();
		httpStatusCodes.add("429");
		httpStatusCodes.add("503");
		return routeLocatorBuilder.routes()
				.route(p -> p.path("/accounts/eazybank/accounts/**").filters(f -> f
						.rewritePath("/accounts/eazybank/accounts/(?<segment>.*)", "/eazybank/accounts/${segment}") // Rewritesave
						.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
						.circuitBreaker(config -> config.setName("default").setStatusCodes(httpStatusCodes)
								.setFallbackUri("forward:/accountsFallBack")))
						.uri("lb://ACCOUNTS"))
				.route(p -> p.path("/loans/loans/**")
						.filters(f -> f.rewritePath("/loans/loans/(?<segment>.*)", "/loans/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.retry(retryConfig -> retryConfig.setRetries(3).setMethods(HttpMethod.GET)
										.setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)
										.setStatuses(HttpStatus.GATEWAY_TIMEOUT, HttpStatus.REQUEST_TIMEOUT)))
						.uri("lb://LOANS"))
				.route(c -> c.path("/cards/cards/**").filters(
						f -> f.rewritePath("/cards/cards/(?<segment>.*)", "/cards/${segment}").requestRateLimiter(
								rl -> rl.setRateLimiter(customRedisRateLimiter()).setKeyResolver(keyResolver())))
						.uri("lb://CARDS"))
				.build();

	}

	@Bean
	Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(5)).build()).build());
	}

	@Bean
	@Primary
	RateLimiter<RedisRateLimiter.Config> customRedisRateLimiter() {
		return new RedisRateLimiter(1, 1, 1);
	}

	@Bean
	KeyResolver keyResolver() {
		return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
				.defaultIfEmpty("anonymous");
	}
}
