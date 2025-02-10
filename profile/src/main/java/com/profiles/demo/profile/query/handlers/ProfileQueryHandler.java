package com.profiles.demo.profile.query.handlers;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import com.profiles.demo.profile.entities.Profile;
import com.profiles.demo.profile.repositories.ProfileRepository;
import lombok.Data;

@Component
@Data
public class ProfileQueryHandler {

	private final ProfileRepository profileRepository;

	@QueryHandler
	public Profile fetchProfileByMobileNumber(String mobileNumber) throws Exception {
		Profile profile = profileRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new Exception("Profile with MobileNumber: " + mobileNumber + " not found"));
		return profile;
	}

}
