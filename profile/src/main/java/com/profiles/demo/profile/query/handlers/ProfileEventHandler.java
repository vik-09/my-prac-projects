package com.profiles.demo.profile.query.handlers;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.common.data.model.base_bom.commonclasses.AccountsChangedEvent;
import com.common.data.model.base_bom.commonclasses.CardsChangedEvent;
import com.profiles.demo.profile.entities.Profile;
import com.profiles.demo.profile.repositories.ProfileRepository;

import lombok.Data;

@Component
@Data
public class ProfileEventHandler {

	private final ProfileRepository profileRepository;

	@EventHandler
	public void computeAccountChangedEvent(AccountsChangedEvent accountChangedEvent) {
		profileRepository.findByMobileNumber(accountChangedEvent.getMobileNumber())
				.ifPresentOrElse((existingProfile) -> {
					existingProfile.setMobileNumber(accountChangedEvent.getMobileNumber());
					if (accountChangedEvent.getAccountNumber() != null) {
						existingProfile.setAccountNumber(accountChangedEvent.getAccountNumber());
					}
					existingProfile.setEmail(accountChangedEvent.getEmail());
					existingProfile.setName(accountChangedEvent.getName());
					profileRepository.save(existingProfile);

				}, () -> {
					Profile newProfile = new Profile();
					newProfile.setMobileNumber(accountChangedEvent.getMobileNumber());
					newProfile.setAccountNumber(accountChangedEvent.getAccountNumber());
					newProfile.setEmail(accountChangedEvent.getEmail());
					newProfile.setName(accountChangedEvent.getName());
					profileRepository.save(newProfile);
				});
	}

	@EventHandler
	public void computeCardsChangedEvent(CardsChangedEvent cardsChangedEvent) throws Exception {
		Profile existingProfile = profileRepository.findByMobileNumber(cardsChangedEvent.getMobileNumber())
				.orElseThrow(() -> {
					return new Exception("Customer With Mobile Number not Found");
				});
		existingProfile.setCardNumber(cardsChangedEvent.getCardNumber());
	}

}
