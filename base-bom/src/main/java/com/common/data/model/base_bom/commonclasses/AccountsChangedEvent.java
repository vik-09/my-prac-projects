package com.common.data.model.base_bom.commonclasses;

import lombok.Data;

@Data
public class AccountsChangedEvent {

	private String mobileNumber;
	private String name;
	private String email;
	private String accountNumber;

}
