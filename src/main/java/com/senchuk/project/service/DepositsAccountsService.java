package com.senchuk.project.service;

import com.senchuk.project.model.Deposit;
import java.util.List;

public interface DepositsAccountsService {

    void createDepositAccounts(Deposit deposit);

    void putInterest(long deposiId, String interestAmount);

    String getInterest(long depositId);

    void getMoneyFromMaster(Deposit deposit);

    void putMoneyToMaster(Deposit deposit);

    void deleteDepositAccount(long depositId);

}
