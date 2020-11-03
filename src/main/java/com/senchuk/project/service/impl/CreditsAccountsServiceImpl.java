package com.senchuk.project.service.impl;

import com.senchuk.project.model.Credit;
import com.senchuk.project.model.CreditsAccounts;
import com.senchuk.project.repository.ChartOfAccountsRepository;
import com.senchuk.project.repository.CreditsAccountsRepository;
import com.senchuk.project.service.CreditsAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

        creditsAccounts.setProfileId(credit.getProfileId());
        creditsAccounts.setCreditId(credit.getId());
        creditsAccounts.setMasterAccountNumber(chartOfAccountsRepository.getAccountNumberByAccountCode("CREDIT_ACCOUNT") + generateNum());
        creditsAccounts.setInterestAccountNumber(chartOfAccountsRepository.getAccountNumberByAccountCode("INTEREST_CREDIT_ACCOUNT") + generateNum());

        creditsAccounts.setMasterAccountBalance("0");
        creditsAccounts.setInterestAccountBalance("0");

        creditsAccountsRepository.save(creditsAccounts);
    }

    @Override
    public void putMoneyOnMasterAccount(long creditId, String amount) {
        CreditsAccounts creditsAccounts = creditsAccountsRepository.findByCreditId(creditId);

        double balance = Double.parseDouble(creditsAccounts.getMasterAccountBalance());

        creditsAccounts.setMasterAccountBalance(Double.toString(balance + Double.parseDouble(amount)));

       creditsAccountsRepository.save(creditsAccounts);
    }

    @Override
    public void putMoneyOnInterestAccount(long creditId, String amount) {
        CreditsAccounts creditsAccounts = creditsAccountsRepository.findByCreditId(creditId);

        double balance = Double.parseDouble(creditsAccounts.getInterestAccountBalance());

        creditsAccounts.setInterestAccountBalance(Double.toString(balance + Double.parseDouble(amount)));

        creditsAccountsRepository.save(creditsAccounts);
    }

    @Override
    public void getMoneyOnMasterAccount(long creditId, String amount) {
        CreditsAccounts creditsAccounts = creditsAccountsRepository.findByCreditId(creditId);

        double balance = Double.parseDouble(creditsAccounts.getMasterAccountBalance());

        creditsAccounts.setMasterAccountBalance(Double.toString(balance - Double.parseDouble(amount)));

        creditsAccountsRepository.save(creditsAccounts);
    }

    @Override
    public void getMoneyOnInterestAccount(long creditId, String amount) {
        CreditsAccounts creditsAccounts = creditsAccountsRepository.findByCreditId(creditId);

        double balance = Double.parseDouble(creditsAccounts.getInterestAccountBalance());

        creditsAccounts.setInterestAccountBalance(Double.toString(balance - Double.parseDouble(amount)));

        creditsAccountsRepository.save(creditsAccounts);
    }

    @Override
    public Double getCommonDebt(long creditId) {

        double masterDebt = Double.parseDouble(creditsAccountsRepository.getMasterAccountBalanceByCreditId(creditId));
        double interestDebt = Double.parseDouble(creditsAccountsRepository.getInterestAccountBalanceByCreditId(creditId));

        return masterDebt + interestDebt;


    }

    @Override
    public boolean checkDebt(long creditId) {
        CreditsAccounts creditsAccounts = creditsAccountsRepository.findByCreditId(creditId);
        double commonBalance = Double.parseDouble(creditsAccounts.getMasterAccountBalance()) + Double.parseDouble(creditsAccounts.getInterestAccountBalance());
        return commonBalance < 0;
    }

    @Override
    @Transactional
    public void deleteAccountByCreditId(long creditId) {
        creditsAccountsRepository.deleteByCreditId(creditId);
    }

    @Override
    public String getMasterAccountDebt(long creditId) {
        return creditsAccountsRepository.getMasterAccountBalanceByCreditId(creditId);
    }

    @Override
    public String getInterestAccountDebt(long creditId) {
        return creditsAccountsRepository.getInterestAccountBalanceByCreditId(creditId);
    }

    private int generateNum() {
        Random random = new Random();
        int rage = 999999999;
        return random.nextInt(rage);
    }

}
