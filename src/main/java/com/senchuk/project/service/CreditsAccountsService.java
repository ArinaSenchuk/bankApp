package com.senchuk.project.service;


import com.senchuk.project.model.Credit;
import com.senchuk.project.model.CreditsAccounts;

import java.util.List;

public interface CreditsAccountsService {

    void createCreditAccounts(Credit credit);

    void putMoneyOnMasterAccount(long creditId, String amount);
    void putMoneyOnInterestAccount(long creditId, String amount);

    void getMoneyOnMasterAccount(long creditId, String amount);
    void getMoneyOnInterestAccount(long creditId, String amount);

    Double getCommonDebt(long creditId);

    boolean checkDebt(long creditId);

    void deleteAccountByCreditId(long creditId);

    String getMasterAccountDebt(long creditId);
    String getInterestAccountDebt(long creditId);
}
