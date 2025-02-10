package gatewayserver2.gateway_server_event_driven.commonclasses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonDto {
	CustomerDto customerDto;
	CardsDto cardsDto;
	LoanDto loanDto;
	
}
