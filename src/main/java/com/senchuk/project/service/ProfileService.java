package com.senchuk.project.service;

import com.senchuk.project.model.Profile;

public interface ProfileService {

    Profile saveProfile(Profile profile);

    Profile saveProfileChanges(Profile profile);

    Profile getProfile();

    boolean deleteProfile();
}
