package com.senchuk.project.controller;

import com.senchuk.project.model.dto.OptionDto;
import com.senchuk.project.service.ReferenceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reference_data", produces = "application/json")
public class ReferenceDataController {

    @Autowired
    private ReferenceDataService referenceDataService;

    @GetMapping(value = "/options")
    public OptionDto getOptions( ) {
        return referenceDataService.getOptions();
    }
}
