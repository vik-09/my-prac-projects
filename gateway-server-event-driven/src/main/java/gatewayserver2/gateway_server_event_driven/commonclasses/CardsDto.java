package gatewayserver2.gateway_server_event_driven.commonclasses;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CardsDto {

	@Pattern(regexp = "^[0-9]{10}$", message = "Enter a Valid Mobile Number")
	private String mobileNumber;
	private String cardNumber;
	private String cardType;
	@Positive
	private double totalLimit;
	@PositiveOrZero
	private double amountUsed;
	@PositiveOrZero
	private double availableAmount;
}
