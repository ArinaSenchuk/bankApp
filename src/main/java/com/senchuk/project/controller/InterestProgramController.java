package com.senchuk.project.controller;

import com.senchuk.project.model.InterestProgram;
import com.senchuk.project.service.InterestProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/interest-program", produces = "application/json")
public class InterestProgramController {

    @Autowired
    private InterestProgramService interestProgramService;

    @GetMapping
    public List<InterestProgram> getAll(){
        return interestProgramService.getAll();
    }
}
