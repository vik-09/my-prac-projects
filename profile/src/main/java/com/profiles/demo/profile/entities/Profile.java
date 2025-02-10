package com.profiles.demo.profile.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "profile")
@Data
public class Profile {

	@Id
	private String id;
	private String mobileNumber;
	private String name;
	private String email;
	@Nullable
	private String accountNumber;
	private String loanNumber;
	private String cardNumber;
}
