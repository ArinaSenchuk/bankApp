package com.senchuk.project.service.impl;

import com.senchuk.project.model.dto.OptionDto;
import com.senchuk.project.repository.ReferenceDataRepository;
import com.senchuk.project.service.ReferenceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="referenceDataService")
public class ReferenceDataServiceImpl implements ReferenceDataService {

    @Autowired
    private ReferenceDataRepository referenceDataRepository;

    @Override
    public OptionDto getOptions() {

        OptionDto optionDto = new OptionDto();
        optionDto.setCity(referenceDataRepository.getReferenceDataByDefinition("city"));
        optionDto.setSex(referenceDataRepository.getReferenceDataByDefinition("sex"));
        optionDto.setStatus(referenceDataRepository.getReferenceDataByDefinition("status"));
        optionDto.setDisability(referenceDataRepository.getReferenceDataByDefinition("disability"));
        optionDto.setNationality(referenceDataRepository.getReferenceDataByDefinition("nationality"));

        return optionDto;
    }
}
