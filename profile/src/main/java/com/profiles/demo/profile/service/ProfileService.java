package com.profiles.demo.profile.service;

import java.util.Optional;
import com.profiles.demo.profile.entities.Profile;

public interface ProfileService {

	public Optional<Profile> fetchProfileByMobileNumber(String mobileNumber);

}
