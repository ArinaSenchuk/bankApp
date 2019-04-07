package com.senchuk.project.service;

import com.senchuk.project.model.ClientAccounts;

public interface ClientAccountsService {

    void saveAccount(long profile_id);

    void getMoneyFromAccount(long profile_id, String amount);

    ClientAccounts getCurrentClientAccount();
}
