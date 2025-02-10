package com.profiles.demo.profile.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.profiles.demo.profile.entities.Profile;
import com.profiles.demo.profile.repositories.ProfileRepository;

import lombok.Data;

@Service
@Data
public class ProfileServiceImpl implements ProfileService {

	private final ProfileRepository profileRepository;

	@Override
	public Optional<Profile> fetchProfileByMobileNumber(String mobileNumber) {
		return profileRepository.findByMobileNumber(mobileNumber);
	}

}
