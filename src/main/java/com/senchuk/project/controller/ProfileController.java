package com.senchuk.project.controller;

import com.senchuk.project.model.Profile;
import com.senchuk.project.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/profiles", produces = "application/json")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping(value = "/profile")
    public Profile getProfile() {
        return profileService.getProfile();
    }

    @PostMapping(value = "/save")
    public void saveProfileChanges(@RequestBody Profile profile){
        profileService.saveProfile(profile);
    }
}
