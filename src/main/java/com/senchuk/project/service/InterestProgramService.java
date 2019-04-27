package com.senchuk.project.service;


import com.senchuk.project.model.InterestProgram;

import java.util.List;

public interface InterestProgramService {

    String getValueOfInterest(long depositTerm, long depositType);

    List<InterestProgram> getAll();
}
