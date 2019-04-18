package com.senchuk.project.service;

import com.senchuk.project.model.dto.CreditOptionDto;
import com.senchuk.project.model.dto.DepositOptionDto;
import com.senchuk.project.model.dto.ProfileOptionDto;


public interface ReferenceDataService {

    ProfileOptionDto getProfileOptions();

    DepositOptionDto getDepositOptions();

    CreditOptionDto getCreditOptions();
}
