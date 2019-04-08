package com.senchuk.project.service.impl;


import com.senchuk.project.repository.ProgramInterestRepository;
import com.senchuk.project.service.ProgramInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value ="programInterestService")
public class ProgramInterestServiceImpl implements ProgramInterestService {

    @Autowired
    private ProgramInterestRepository programInterestRepository;

    @Override
    public String getValueOfInterest(long depositTerm, long depositType) {

        return programInterestRepository.getValueOfInterest(depositTerm,depositType);
    }
}
