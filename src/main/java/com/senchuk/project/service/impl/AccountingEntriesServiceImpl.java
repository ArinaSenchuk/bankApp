package com.senchuk.project.service.impl;

import com.senchuk.project.model.AccountingEntries;
import com.senchuk.project.model.Deposit;
import com.senchuk.project.repository.AccountingEntriesRepository;
import com.senchuk.project.repository.ChartOfAccountsRepository;
import com.senchuk.project.repository.UserRepository;
import com.senchuk.project.service.AccountingEntriesService;
import com.senchuk.project.service.BankDevelopmentFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "depositsEntryService")
public class AccountingEntriesServiceImpl implements AccountingEntriesService {

    @Autowired
    private AccountingEntriesRepository accountingEntriesRepository;
    @Autowired
    private ChartOfAccountsRepository chartOfAccountsRepository;
    @Autowired
    private BankDevelopmentFundService bankDevelopmentFundService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityHelper securityHelper;

    @Override
    public void startDepositProgram(Deposit deposit) {

        //transferFromPersonalAccToDepositAcc(depositId, depositAmount, depositTypeCode);
        transferFromDepositAccToBankDevFund(deposit.getDepositAmount(), deposit.getDepositType().getCode());

    }


    private void transferFromPersonalAccToDepositAcc(String depositAmount, String depositTypeCode) {
        AccountingEntries accountingEntries = new AccountingEntries();

        accountingEntries.setProfile_id(userRepository.getProfileIdByLogin(securityHelper.getCurrentUsername()));
        accountingEntries.setAmount(depositAmount);
        accountingEntries.setDebet(chartOfAccountsRepository.getAccountNumberByAccountCode("CURRENT_ACCOUNT"));
        accountingEntries.setCredit(chartOfAccountsRepository.getAccountNumberByAccountCode("ACCOUNT_FOR_" + depositTypeCode));

        accountingEntriesRepository.save(accountingEntries);
    }

    private void transferFromDepositAccToBankDevFund(String depositAmount, String depositTypeCode) {
        AccountingEntries accountingEntries = new AccountingEntries();

        accountingEntries.setProfile_id(userRepository.getProfileIdByLogin(securityHelper.getCurrentUsername()));
        accountingEntries.setAmount(depositAmount);
        accountingEntries.setDebet(chartOfAccountsRepository.getAccountNumberByAccountCode("ACCOUNT_FOR_" + depositTypeCode));
        accountingEntries.setCredit(chartOfAccountsRepository.getAccountNumberByAccountCode("BANK_DEVELOPMENT_FUND_ACCOUNT"));

        bankDevelopmentFundService.putMoneyOnTheAccount(depositAmount);

        accountingEntriesRepository.save(accountingEntries);
    }

    @Override
    public void chargeInterestOnDeposits(Deposit deposit, String interestAmount) {
        AccountingEntries accountingEntries = new AccountingEntries();

        accountingEntries.setProfile_id(userRepository.getProfileIdByLogin(securityHelper.getCurrentUsername()));
        accountingEntries.setDebet(chartOfAccountsRepository.getAccountNumberByAccountCode("BANK_DEVELOPMENT_FUND_ACCOUNT"));
        accountingEntries.setCredit(chartOfAccountsRepository.getAccountNumberByAccountCode("INTEREST_ACCOUNT_FOR_" + deposit.getDepositType().getCode()));
        accountingEntries.setAmount(interestAmount);

        accountingEntriesRepository.save(accountingEntries);
    }

    @Override
    public void putMoneyOnCashbox(String amount) {
        AccountingEntries accountingEntries = new AccountingEntries();

        accountingEntries.setProfile_id(userRepository.getProfileIdByLogin(securityHelper.getCurrentUsername()));
        accountingEntries.setDebet(chartOfAccountsRepository.getAccountNumberByAccountCode("CASHBOX"));
        accountingEntries.setCredit("");
        accountingEntries.setAmount(amount);

        accountingEntriesRepository.save(accountingEntries);
        putUserMoneyOnPersonalAccountFromCashBox(amount);
    }


    private void putUserMoneyOnPersonalAccountFromCashBox(String amount) {
        AccountingEntries accountingEntries = new AccountingEntries();

        accountingEntries.setProfile_id(userRepository.getProfileIdByLogin(securityHelper.getCurrentUsername()));
        accountingEntries.setDebet("");
        accountingEntries.setCredit(chartOfAccountsRepository.getAccountNumberByAccountCode("CASHBOX") + " / " + chartOfAccountsRepository.getAccountNumberByAccountCode("CURRENT_ACCOUNT"));
        accountingEntries.setAmount(amount);

        accountingEntriesRepository.save(accountingEntries);
    }

    @Override
    public void withdrawMoney(String amount) {
        AccountingEntries accountingEntries = new AccountingEntries();

        accountingEntries.setProfile_id(userRepository.getProfileIdByLogin(securityHelper.getCurrentUsername()));
        accountingEntries.setDebet(chartOfAccountsRepository.getAccountNumberByAccountCode("CURRENT_ACCOUNT") + " / " + chartOfAccountsRepository.getAccountNumberByAccountCode("CASHBOX"));

        accountingEntries.setCredit("");
        accountingEntries.setAmount(amount);
        accountingEntriesRepository.save(accountingEntries);

        getMoneyFromCashbox(amount);

    }

    private void getMoneyFromCashbox(String amount) {
        AccountingEntries accountingEntries = new AccountingEntries();

        accountingEntries.setProfile_id(userRepository.getProfileIdByLogin(securityHelper.getCurrentUsername()));
        accountingEntries.setDebet(" ");
        accountingEntries.setCredit(chartOfAccountsRepository.getAccountNumberByAccountCode("CASHBOX"));
        accountingEntries.setAmount(amount);

        accountingEntriesRepository.save(accountingEntries);
    }
}
