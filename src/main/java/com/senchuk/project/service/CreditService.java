package com.senchuk.project.service;


import com.senchuk.project.model.Credit;
import com.senchuk.project.model.dto.CreditDto;

import java.util.List;

public interface CreditService {

    Credit save(Credit credit);

    List<Credit> getAllCredits();

    List<Credit> getAllCreditsByProfileId(long profileId);

    List<CreditDto> getAllCreditsOfCurrentUser();

    String getMainDebt(Credit credit);

    String getInterestDebt(Credit credit);

    void deleteById(long creditId);

}
