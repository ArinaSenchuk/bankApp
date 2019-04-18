package com.senchuk.project.service.impl;

import com.senchuk.project.model.dto.CreditOptionDto;
import com.senchuk.project.model.dto.DepositOptionDto;
import com.senchuk.project.model.dto.ProfileOptionDto;
import com.senchuk.project.repository.ReferenceDataRepository;
import com.senchuk.project.service.ReferenceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="referenceDataService")
public class ReferenceDataServiceImpl implements ReferenceDataService {

    @Autowired
    private ReferenceDataRepository referenceDataRepository;

    @Override
    public ProfileOptionDto getProfileOptions() {

        ProfileOptionDto profileOptionDto = new ProfileOptionDto();
        profileOptionDto.setCity(referenceDataRepository.getReferenceDataByDefinition("city"));
        profileOptionDto.setSex(referenceDataRepository.getReferenceDataByDefinition("sex"));
        profileOptionDto.setStatus(referenceDataRepository.getReferenceDataByDefinition("status"));
        profileOptionDto.setDisability(referenceDataRepository.getReferenceDataByDefinition("disability"));
        profileOptionDto.setNationality(referenceDataRepository.getReferenceDataByDefinition("nationality"));

        return profileOptionDto;
    }

    @Override
    public DepositOptionDto getDepositOptions() {

        DepositOptionDto depositOptionDto = new DepositOptionDto();

        depositOptionDto.setCurrencyType(referenceDataRepository.getReferenceDataByDefinition("currencyType"));
        depositOptionDto.setDepositTerm(referenceDataRepository.getReferenceDataByDefinition("depositTerm"));
        depositOptionDto.setDepositType(referenceDataRepository.getReferenceDataByDefinition("depositType"));

        return depositOptionDto;
    }

    @Override
    public CreditOptionDto getCreditOptions() {

        CreditOptionDto creditOptionDto  = new CreditOptionDto();

        creditOptionDto.setCreditTerm(referenceDataRepository.getReferenceDataByDefinition("creditTerm"));
        creditOptionDto.setCreditType(referenceDataRepository.getReferenceDataByDefinition("creditType"));
        creditOptionDto.setCurrencyType(referenceDataRepository.getReferenceDataByDefinition("currencyType"));

        return creditOptionDto;
    }

}
