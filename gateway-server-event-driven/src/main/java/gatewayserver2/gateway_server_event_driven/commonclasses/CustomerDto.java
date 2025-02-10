package gatewayserver2.gateway_server_event_driven.commonclasses;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

	@NotEmpty(message = "Name should not be empty")
	@Size(min = 3, max = 10, message = "Name should be between 2 and 10 characters")

	private String name;
	@NotEmpty(message = "Email should not be empty")
	@Email(message = "Email should be valid")

	private String email;
	@Pattern(regexp = "^(?:\\+91|91|0)?[6-9]\\d{9}$", message = "Enter a Valid Mobile Number")

	private String mobileNumber;
	private AccountsDto accountsDto;
}
