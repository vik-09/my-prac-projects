package gatewayserver2.gateway_server_event_driven.commonclasses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AccountsDto {

	private Long accountNumber;
	private String accountType;
	private String branchAddress;

}
