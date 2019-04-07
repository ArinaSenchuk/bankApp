package com.senchuk.project.controller;


import com.senchuk.project.model.Profile;
import com.senchuk.project.model.dto.RegistrationDto;
import com.senchuk.project.service.ClientAccountsService;
import com.senchuk.project.service.ProfileService;
import com.senchuk.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ClientAccountsService clientAccountsService;

    @PostMapping(value = "/save")
    public void save(@RequestBody RegistrationDto registrationDto) {
        Profile profile = profileService.saveProfile(registrationDto.getProfile());
        userService.saveUser(registrationDto.getUser(), profile.getId());
        clientAccountsService.saveAccount(profile.getId());
    }
}
