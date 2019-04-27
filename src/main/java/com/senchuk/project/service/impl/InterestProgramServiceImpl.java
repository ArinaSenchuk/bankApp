package com.senchuk.project.service.impl;


import com.senchuk.project.model.InterestProgram;
import com.senchuk.project.repository.InterestProgramRepository;
import com.senchuk.project.service.InterestProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value ="interestProgramService")
public class InterestProgramServiceImpl implements InterestProgramService {

    @Autowired
    private InterestProgramRepository interestProgramRepository;

    @Override
    public String getValueOfInterest(long depositTerm, long depositType) {

        return interestProgramRepository.getValueOfInterest(depositTerm,depositType);
    }

    @Override
    public List<InterestProgram> getAll() {
        return interestProgramRepository.findAll();
    }
}
