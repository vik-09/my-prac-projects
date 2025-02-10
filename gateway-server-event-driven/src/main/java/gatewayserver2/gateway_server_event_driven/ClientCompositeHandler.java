package gatewayserver2.gateway_server_event_driven;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import gatewayserver2.gateway_server_event_driven.commonclasses.CardsDto;
import gatewayserver2.gateway_server_event_driven.commonclasses.CommonDto;
import gatewayserver2.gateway_server_event_driven.commonclasses.CustomerDto;
import gatewayserver2.gateway_server_event_driven.commonclasses.LoanDto;
import reactor.core.publisher.Mono;

@Configuration
public class ClientCompositeHandler {

	ClientCallInterface clientCallInterface;

	public ClientCompositeHandler(ClientCallInterface clientCallInterface) {
		this.clientCallInterface = clientCallInterface;
	}

	public Mono<ServerResponse> fetchSummary(ServerRequest serverRequest) {
		Mono<ResponseEntity<CustomerDto>> accountsResponse = clientCallInterface
				.fetchAccountDetails(serverRequest.queryParam("mobileNumber").get());
		Mono<ResponseEntity<CustomerDto>> accountsResponse2 = clientCallInterface
				.fetchAccountDetails(serverRequest.queryParam("mobileNumber").get());
		return Mono.zip(accountsResponse, accountsResponse2).flatMap(tuple -> {
			tuple.getT1().getBody();
			tuple.getT2().getBody();
			CommonDto commonDto = new CommonDto(tuple.getT1().getBody(), new CardsDto(), new LoanDto());
			return ServerResponse.ok().body(BodyInserters.fromValue(commonDto));
		});
	}

}
