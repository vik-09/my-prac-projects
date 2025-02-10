package com.profiles.demo.profile.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.profiles.demo.profile.entities.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {

	public Optional<Profile> findByMobileNumber(String mobileNumber);

}
