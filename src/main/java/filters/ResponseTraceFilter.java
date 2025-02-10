package filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class ResponseTraceFilter {

	private static final Logger logger = LoggerFactory.getLogger(ResponseTraceFilter.class);

	@Autowired
	FilterUtility filterUtility;

	@Bean
	GlobalFilter postGlobalFilter() {
		return (exchange, chain) -> {
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				String correlationId = filterUtility.getCorrelationId(exchange);
				logger.debug("Updated the correlation id to the outbound headers: {}", correlationId);
				exchange.getResponse().getHeaders().add(filterUtility.generateCorrelationId(), correlationId);
			}));
		};
	}
}