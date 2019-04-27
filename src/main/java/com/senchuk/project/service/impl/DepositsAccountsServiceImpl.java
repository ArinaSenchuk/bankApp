package com.senchuk.project.service.impl;

import com.senchuk.project.model.Deposit;
import com.senchuk.project.model.DepositsAccounts;
import com.senchuk.project.repository.ChartOfAccountsRepository;
import com.senchuk.project.repository.DepositsAccountsRepository;
import com.senchuk.project.service.DepositsAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

        depositsAccounts.setProfileId(deposit.getProfileId());
        depositsAccounts.setDepositId(deposit.getId());
        depositsAccounts.setMasterAccountBalance(deposit.getDepositAmount());
        depositsAccounts.setInterestAccountBalance("0");

        depositsAccounts.setMasterAccountNumber(chartOfAccountsRepository.getAccountNumberByAccountCode("ACCOUNT_FOR_" + deposit.getDepositType().getCode()) + generateNum());
        depositsAccounts.setInterestAccountNumber(chartOfAccountsRepository.getAccountNumberByAccountCode("INTEREST_ACCOUNT_FOR_" + deposit.getDepositType().getCode()) + generateNum());

        depositsAccountsRepository.save(depositsAccounts);
    }

    @Override
    public void putInterest(long depositId, String interestAmount) {

        DepositsAccounts depositsAccounts = depositsAccountsRepository.findByDepositId(depositId);

        double amount = Double.parseDouble(depositsAccounts.getInterestAccountBalance()) + Double.parseDouble(interestAmount);
        depositsAccounts.setInterestAccountBalance(Double.toString(amount));

        depositsAccountsRepository.save(depositsAccounts);
    }

    @Override
    public String getInterest(long depositId) {
        DepositsAccounts depositsAccounts = depositsAccountsRepository.findByDepositId(depositId);
        String interest = depositsAccounts.getInterestAccountBalance();
        depositsAccounts.setInterestAccountBalance("0");

        depositsAccountsRepository.save(depositsAccounts);
        return interest;
    }

    @Override
    public void getMoneyFromMaster(Deposit deposit) {
        DepositsAccounts depositsAccounts = depositsAccountsRepository.findByDepositId(deposit.getId());

        double amount = Double.parseDouble(depositsAccounts.getMasterAccountBalance());

        depositsAccounts.setMasterAccountBalance(Double.toString(amount - Double.parseDouble(deposit.getDepositAmount())));

        depositsAccountsRepository.save(depositsAccounts);
    }

    @Override
    public void putMoneyToMaster(Deposit deposit) {
        DepositsAccounts depositsAccounts = depositsAccountsRepository.findByDepositId(deposit.getId());
        double amount = Double.parseDouble(depositsAccounts.getMasterAccountBalance());

        depositsAccounts.setMasterAccountBalance(Double.toString(amount + Double.parseDouble(deposit.getDepositAmount())));
        depositsAccountsRepository.save(depositsAccounts);
    }

    @Transactional
    @Override
    public void deleteDepositAccount(long depositId) {
        depositsAccountsRepository.deleteByDepositId(depositId);
    }


    private int generateNum() {
        Random random = new Random();
        int rage = 999999999;
        return random.nextInt(rage);
    }
}
