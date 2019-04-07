package com.senchuk.project.service.impl;

import com.senchuk.project.model.AccountingEntries;
import com.senchuk.project.model.Deposit;
import com.senchuk.project.repository.AccountingEntriesRepository;
import com.senchuk.project.repository.ChartOfAccountsRepository;
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

    @Override
    public void startDepositProgram(Deposit deposit) {

        //transferFromPersonalAccToDepositAcc(deposit_id, depositAmount, depositTypeCode);
        transferFromDepositAccToBankDevFund(deposit.getId(), deposit.getDepositAmount(), deposit.getDepositType().getCode());

    }






    private void transferFromPersonalAccToDepositAcc(long deposit_id, String depositAmount, String depositTypeCode) {
        AccountingEntries accountingEntries = new AccountingEntries();

        accountingEntries.setDeposit_id(deposit_id);
        accountingEntries.setAmount(depositAmount);
        accountingEntries.setDebet(chartOfAccountsRepository.getAccountNumberByAccountCode("CURRENT_ACCOUNT"));
        accountingEntries.setCredit(chartOfAccountsRepository.getAccountNumberByAccountCode("ACCOUNT_FOR_" + depositTypeCode));

        accountingEntriesRepository.save(accountingEntries);
    }

    private void transferFromDepositAccToBankDevFund(long deposit_id, String depositAmount, String depositTypeCode) {
        AccountingEntries accountingEntries = new AccountingEntries();

        accountingEntries.setDeposit_id(deposit_id);
        accountingEntries.setAmount(depositAmount);
        accountingEntries.setDebet(chartOfAccountsRepository.getAccountNumberByAccountCode("ACCOUNT_FOR_" + depositTypeCode));
        accountingEntries.setCredit(chartOfAccountsRepository.getAccountNumberByAccountCode("BANK_DEVELOPMENT_FUND_ACCOUNT"));

        bankDevelopmentFundService.putMoneyOnTheAccount(depositAmount);

        accountingEntriesRepository.save(accountingEntries);
    }
}
