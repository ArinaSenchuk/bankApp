package com.senchuk.project.service;

import com.senchuk.project.model.Deposit;
import java.util.List;

public interface DepositsAccountsService {

    void createDepositAccounts(Deposit deposit);

    void putInterest(long deposit_id, String interestAmount);


}
