package com.senchuk.project.service.impl;

import com.senchuk.project.model.Deposit;
import com.senchuk.project.model.DepositsAccounts;
import com.senchuk.project.repository.ChartOfAccountsRepository;
import com.senchuk.project.repository.DepositsAccountsRepository;
import com.senchuk.project.service.DepositsAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service(value = "depositsAccountsService")
public class DepositsAccountsServiceImpl implements DepositsAccountsService {

    @Autowired
    private DepositsAccountsRepository depositsAccountsRepository;
    @Autowired
    private ChartOfAccountsRepository chartOfAccountsRepository;


    @Override
    public void createDepositAccounts(Deposit deposit) {
        DepositsAccounts depositsAccounts = new DepositsAccounts();

        depositsAccounts.setProfile_id(deposit.getProfileId());
        depositsAccounts.setDepositId(deposit.getId());
        depositsAccounts.setMaster_account_balance(deposit.getDepositAmount());
        depositsAccounts.setInterest_account_balance("0");

        depositsAccounts.setMaster_account_number(chartOfAccountsRepository.getAccountNumberByAccountCode("ACCOUNT_FOR_" + deposit.getDepositType().getCode()) + generateNum());
        depositsAccounts.setInterest_account_number(chartOfAccountsRepository.getAccountNumberByAccountCode("INTEREST_ACCOUNT_FOR_" + deposit.getDepositType().getCode()) + generateNum());

        depositsAccountsRepository.save(depositsAccounts);
    }

    @Override
    public void putInterest(long depositId, String interestAmount) {

        DepositsAccounts depositsAccounts = depositsAccountsRepository.findByDepositId(depositId);

        double amount = Double.parseDouble(depositsAccounts.getInterest_account_balance()) + Double.parseDouble(interestAmount);
        depositsAccounts.setInterest_account_balance(Double.toString(amount));

        depositsAccountsRepository.save(depositsAccounts);
    }


    private int generateNum() {
        Random random = new Random();
        int rage = 999999999;
        return random.nextInt(rage);
    }
}
