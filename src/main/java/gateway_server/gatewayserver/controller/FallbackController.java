package gateway_server.gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

	@RequestMapping(path = "/accountsFallBack")
	public Mono<String> accountsFallBack() {
		return Mono.just("Accounts service is down");
	}

}
