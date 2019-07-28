package com.senchuk.project.service;


import com.senchuk.project.model.Credit;

import java.util.List;

public interface CreditService {

    Credit save(Credit credit);

    List<Credit> getAllCredits();

    List<Credit> getAllCreditsOfCurrentUser();

    String getMainDebt(Credit credit);

    String getInterestDebt(Credit credit);

}
