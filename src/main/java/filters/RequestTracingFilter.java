package filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class RequestTracingFilter implements GlobalFilter {

	@Autowired
	FilterUtility filterUtility;

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		if (filterUtility.checkForCorrelationId(exchange)) {
			logger.info("CORRELATION_ID is {} " + filterUtility.getCorrelationId(exchange));
		} else {
			exchange.getRequest().mutate().header("CORRELATION_ID", filterUtility.generateCorrelationId()).build();
		}
		return chain.filter(exchange);
	}

}
