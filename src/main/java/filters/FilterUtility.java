package filters;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtility {

	public boolean checkForCorrelationId(ServerWebExchange exchange) {
		if (exchange.getRequest().getHeaders().get("CORRELATION_ID") != null) {
			return true;
		}
		return false;
	}

	public String getCorrelationId(ServerWebExchange exchange) {
		return exchange.getRequest().getHeaders().get("CORRELATION_ID").stream().findFirst().get();
	}

	public String generateCorrelationId() {
		return UUID.randomUUID().toString();
	}
}
