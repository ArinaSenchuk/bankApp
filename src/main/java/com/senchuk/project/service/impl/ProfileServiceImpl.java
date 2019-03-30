package com.senchuk.project.service.impl;

import com.senchuk.project.model.Profile;
import com.senchuk.project.repository.ProfileRepository;
import com.senchuk.project.repository.UserRepository;
import com.senchuk.project.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "profileService")
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityHelper securityHelper;

    @Override
    public Profile saveProfile(Profile profile) {
        if (profileRepository.isExists(profile.getIdentificationNumber(), profile.getPassportNumber()).isEmpty()) {
            return profileRepository.save(profile);
        } else
            throw new IllegalStateException("User with this identification number or passport number is already exist");
    }

    @Override
    public Profile saveProfileChanges(Profile profile) {
        List<Long> ids = profileRepository.isExists(profile.getIdentificationNumber(), profile.getPassportNumber());
        if (ids.size() == 0 || (ids.size() < 2 && ids.contains(profile.getId()))) {
            return profileRepository.save(profile);
        } else
            throw new IllegalStateException("User with this identification number or passport number is already exist");
    }

    @Override
    public Profile getProfile() {
        long id = userRepository.getProfileIdByLogin(securityHelper.getCurrentUsername());
        return profileRepository.findById(id);
    }
}
