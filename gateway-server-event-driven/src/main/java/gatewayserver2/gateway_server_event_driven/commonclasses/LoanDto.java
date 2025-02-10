package gatewayserver2.gateway_server_event_driven.commonclasses;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class LoanDto {

	@Pattern(regexp = "^[0-9]{10}$", message = "Enter a Valid Mobile Number")
	private String mobileNumber;
	private String loanNumber;
	private String loanType;
	@Positive
	private double totalLoan;
	@PositiveOrZero
	private double amountPaid;
	@PositiveOrZero
	private double outstandingAmount;

}
