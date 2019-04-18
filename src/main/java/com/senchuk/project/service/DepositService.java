package com.senchuk.project.service;


import com.senchuk.project.model.Deposit;

import java.util.List;

public interface DepositService {

    Deposit saveDeposit(Deposit deposit);

    List<Deposit> getDeposits();

    String getDailyCharge(Deposit deposit);

    List<Deposit> getAllDepositsOfCurrentUser();

}
