package gatewayserver2.gateway_server_event_driven.call.forwarding;

import java.time.LocalDateTime;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

public class RouteForwarder {

	public RouteLocator forwarder(RouteLocatorBuilder routeBuilder) {
		return routeBuilder.routes()
				.route(p -> p.path("/accounts/eazybank/accounts/**").filters(
						f -> f.rewritePath("accounts/eazybank/accounts/(?<segment>.*)", "/eazybank/accounts/${segment}")
								.addResponseHeader("X-RESPONSE-TIME", LocalDateTime.now().toString()))
						.uri("lb:ACCOUNTS"))
				.route(p -> p.path("/loans/loans/**")
						.filters(f -> f.rewritePath("/loans/loans/(?<segment>.*)", "/loans/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://LOANS"))
				.route(c -> c.path("/cards/cards/**")
						.filters(f -> f.rewritePath("/cards/cards/(?<segment>.*)", "/cards/${segment}"))
						.uri("lb://CARDS"))
				.build();
	}

}
