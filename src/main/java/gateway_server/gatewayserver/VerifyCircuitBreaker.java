package gateway_server.gatewayserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import jakarta.annotation.PostConstruct;

@Component
public class VerifyCircuitBreaker {

	@Autowired
	private CircuitBreakerRegistry circuitBreakerRegistry;

	@PostConstruct
	public void logCircuitBreakerInfo() {
		CircuitBreaker accountsCircuitBreaker = circuitBreakerRegistry.circuitBreaker("default");

	}
}