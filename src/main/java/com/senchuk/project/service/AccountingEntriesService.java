package com.senchuk.project.service;

import com.senchuk.project.model.Deposit;

public interface AccountingEntriesService {

    void startDepositProgram(Deposit deposit);

    void chargeInterestOnDeposits(Deposit deposit, String interestAmount);

    void transferFromPersonalAccToDepositAcc(Deposit deposit);

    void putMoneyOnCashbox(String amount);

    void withdrawMoney(String amount);

    void endDepositProgram(Deposit deposit);

}
