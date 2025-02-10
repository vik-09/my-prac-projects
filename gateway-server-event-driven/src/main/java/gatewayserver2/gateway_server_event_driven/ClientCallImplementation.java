package gatewayserver2.gateway_server_event_driven;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientCallImplementation {

	@Value("${base.url}")
	private String baseUrl;

	@Bean
	ClientCallInterface implementClientCall() {
		WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
		WebClientAdapter webClientAdapter = WebClientAdapter.create(webClient);
		HttpServiceProxyFactory httpServieProxyFactory = HttpServiceProxyFactory.builderFor(webClientAdapter).build();
		return httpServieProxyFactory.createClient(ClientCallInterface.class);
	}

}
