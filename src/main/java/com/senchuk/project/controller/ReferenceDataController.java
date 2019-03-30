package com.senchuk.project.controller;

import com.senchuk.project.model.dto.DepositOptionDto;
import com.senchuk.project.model.dto.ProfileOptionDto;
import com.senchuk.project.service.ReferenceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reference_data/options", produces = "application/json")
public class ReferenceDataController {

    @Autowired
    private ReferenceDataService referenceDataService;

    @GetMapping(value = "/profile")
    public ProfileOptionDto getProfileOptions( ) {
        return referenceDataService.getProfileOptions();
    }

    @GetMapping(value = "/deposit")
    public DepositOptionDto getDepositOptions() {
        return referenceDataService.getDepositOptions();
    }
}
