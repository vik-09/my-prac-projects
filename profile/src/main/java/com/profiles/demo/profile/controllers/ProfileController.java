package com.profiles.demo.profile.controllers;

import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.profiles.demo.profile.entities.Profile;
import com.profiles.demo.profile.query.commonclasses.ProfileQueryClass;

import lombok.Data;

@RestController
@Data
public class ProfileController {

	private final QueryGateway queryGateway;

	@GetMapping
	public ResponseEntity<Profile> fetchProfile(@RequestParam String mobilenumber) {
		return ResponseEntity.ok(queryGateway.query(new ProfileQueryClass(mobilenumber), Profile.class).join());
	}

}
