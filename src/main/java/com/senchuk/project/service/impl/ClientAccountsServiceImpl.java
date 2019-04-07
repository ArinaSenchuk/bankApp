package com.senchuk.project.service.impl;

import com.senchuk.project.model.ClientAccounts;
import com.senchuk.project.repository.ChartOfAccountsRepository;
import com.senchuk.project.repository.ClientAccountsRepository;
import com.senchuk.project.repository.UserRepository;
import com.senchuk.project.service.ClientAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service(value="clientAccountsService")
public class ClientAccountsServiceImpl implements ClientAccountsService {

    @Autowired
    private ClientAccountsRepository clientAccountsRepository;

    @Autowired
    private ChartOfAccountsRepository chartOfAccountsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityHelper securityHelper;

    @Override
    public void saveAccount(long profile_id) {
        ClientAccounts clientAccounts = new ClientAccounts();
        clientAccounts.setProfile_id(profile_id);
        clientAccounts.setAccountNumber(chartOfAccountsRepository.getAccountNumberByAccountCode("CURRENT_ACCOUNT") + generateNum());
        clientAccounts.setAccountBalance("0");

        clientAccountsRepository.save(clientAccounts);
    }

    @Override
    public void getMoneyFromAccount(long profile_id, String amount) {

    }

    @Override
    public ClientAccounts getCurrentClientAccount() {
        return clientAccountsRepository.findByProfileId(userRepository.getProfileIdByLogin(securityHelper.getCurrentUsername()));
    }





    private int generateNum() {
        Random random = new Random();
        int rage = 999999999;
        return random.nextInt(rage);
    }

}
