package com.senchuk.project.service;


import com.senchuk.project.model.Credit;

public interface CreditsAccountsService {

    void createCreditAccounts(Credit credit);

    void putMoneyOnMasterAccount(Credit credit, String amount);

    void getMoneyOnMasterAccount(Credit credit, String amount);

    void getMoneyOnInterestAccount(Credit credit, String amount);
}
