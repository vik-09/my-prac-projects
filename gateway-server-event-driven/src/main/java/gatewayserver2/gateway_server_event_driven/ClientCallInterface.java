package gatewayserver2.gateway_server_event_driven;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.PostExchange;

import gatewayserver2.gateway_server_event_driven.commonclasses.CustomerDto;
import jakarta.ws.rs.core.MediaType;
import reactor.core.publisher.Mono;

public interface ClientCallInterface {

	@PostExchange(url = "/accounts/eazybank/accounts/findByMobileNumber", accept = MediaType.APPLICATION_JSON)
	public Mono<ResponseEntity<CustomerDto>> fetchAccountDetails(@RequestParam("mobileNumber") String mobileNumber);

}
