package gatewayserver2.gateway_server_event_driven;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterController {
	@Bean
	RouterFunction<ServerResponse> route(ClientCompositeHandler handler) {

		return RouterFunctions
				.route(RequestPredicates.GET("/fetchSummary").and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
						.and(RequestPredicates.queryParam("mobileNumber", param -> true)), handler::fetchSummary);

	}

}
 