package com.senchuk.project.service.impl;

import com.senchuk.project.model.Credit;
import com.senchuk.project.model.CreditsAccounts;
import com.senchuk.project.repository.ChartOfAccountsRepository;
import com.senchuk.project.repository.CreditsAccountsRepository;
import com.senchuk.project.service.CreditsAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service(value = "creditsAccountsService")
public class CreditsAccountsServiceImpl implements CreditsAccountsService {

    @Autowired
    private CreditsAccountsRepository creditsAccountsRepository;
    @Autowired
    private ChartOfAccountsRepository chartOfAccountsRepository;


    @Override
    public void createCreditAccounts(Credit credit) {
        CreditsAccounts creditsAccounts = new CreditsAccounts();

        creditsAccounts.setProfile_id(credit.getProfile_id());
        creditsAccounts.setCredit_id(credit.getId());
        creditsAccounts.setMaster_account_number(chartOfAccountsRepository.getAccountNumberByAccountCode("CREDIT_ACCOUNT") + generateNum());
        creditsAccounts.setInterest_account_number(chartOfAccountsRepository.getAccountNumberByAccountCode("INTEREST_CREDIT_ACCOUNT") + generateNum());

        creditsAccounts.setMaster_account_balance(credit.getCreditAmount());
        creditsAccounts.setInterest_account_balance("0");

        creditsAccountsRepository.save(creditsAccounts);
    }



    private int generateNum() {
        Random random = new Random();
        int rage = 999999999;
        return random.nextInt(rage);
    }

}
