package com.senchuk.project.service;

import com.senchuk.project.model.ClientAccounts;

public interface ClientAccountsService {

    void saveAccount(long profile_id);

    void getMoneyFromAccount(String amount);

    void putMoneyFromAccount(String amount);

    ClientAccounts getCurrentClientAccount();
}
