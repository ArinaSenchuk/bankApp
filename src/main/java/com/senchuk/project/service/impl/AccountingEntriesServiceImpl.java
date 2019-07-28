package com.senchuk.project.service.impl;

import com.senchuk.project.model.AccountingEntries;
import com.senchuk.project.model.Credit;
import com.senchuk.project.model.Deposit;
import com.senchuk.project.repository.AccountingEntriesRepository;
import com.senchuk.project.repository.ChartOfAccountsRepository;
import com.senchuk.project.repository.UserRepository;
import com.senchuk.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "accountingEntryService")
public class AccountingEntriesServiceImpl implements AccountingEntriesService {

    @Autowired
    private AccountingEntriesRepository accountingEntriesRepository;
    @Autowired
    private ChartOfAccountsRepository chartOfAccountsRepository;
    @Autowired
    private BankDevelopmentFundService bankDevelopmentFundService;
    @Autowired
    private ClientAccountsService clientAccountsService;
    @Autowired
    private DepositsAccountsService depositsAccountsService;
    @Autowired
    private DepositService depositService;
    @Autowired
    private CreditsAccountsService creditsAccountsService;
    @Autowired
    private CreditService creditService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityHelper securityHelper;

    @Override
    public void startDepositProgram(Deposit deposit) {
        transferFromDepositAccToBankDevFund(deposit);
    }

    @Override
    public void endDepositProgram(Deposit deposit) {
        transferFromBankDevFundToDepositAcc(deposit);
        transferFromDepositAccTOPersonalAcc(deposit);
        transferFromInterestAccToPersonalAcc(deposit);

        depositsAccountsService.deleteDepositAccount(deposit.getId());
        depositService.deleteDeposit(deposit.getId());
    }


    @Override
    public void transferFromPersonalAccToDepositAcc(Deposit deposit) {
        AccountingEntries accountingEntries = new AccountingEntries();

        accountingEntries.setProfile_id(deposit.getProfileId());
        accountingEntries.setAmount(deposit.getDepositAmount());
        accountingEntries.setDebet(chartOfAccountsRepository.getAccountNumberByAccountCode("CURRENT_ACCOUNT"));
        accountingEntries.setCredit(chartOfAccountsRepository.getAccountNumberByAccountCode("ACCOUNT_FOR_" + deposit.getDepositType().getCode()));

        clientAccountsService.getMoneyFromAccount(deposit.getDepositAmount());

        accountingEntriesRepository.save(accountingEntries);
    }

    private void transferFromDepositAccTOPersonalAcc(Deposit deposit) {
        AccountingEntries accountingEntries = new AccountingEntries();

        accountingEntries.setProfile_id(deposit.getProfileId());
        accountingEntries.setAmount(deposit.getDepositAmount());
        accountingEntries.setDebet(chartOfAccountsRepository.getAccountNumberByAccountCode("ACCOUNT_FOR_" + deposit.getDepositType().getCode()));
        accountingEntries.setCredit(chartOfAccountsRepository.getAccountNumberByAccountCode("CURRENT_ACCOUNT"));

        clientAccountsService.putMoneyFromAccount(deposit.getDepositAmount(), deposit.getProfileId());
        depositsAccountsService.getMoneyFromMaster(deposit);

        accountingEntriesRepository.save(accountingEntries);
    }


    private void transferFromDepositAccToBankDevFund(Deposit deposit) {
        AccountingEntries accountingEntries = new AccountingEntries();

        accountingEntries.setProfile_id(deposit.getProfileId());
        accountingEntries.setAmount(deposit.getDepositAmount());
        accountingEntries.setDebet(chartOfAccountsRepository.getAccountNumberByAccountCode("ACCOUNT_FOR_" + deposit.getDepositType().getCode()));
        accountingEntries.setCredit(chartOfAccountsRepository.getAccountNumberByAccountCode("BANK_DEVELOPMENT_FUND_ACCOUNT"));

        depositsAccountsService.getMoneyFromMaster(deposit);
        bankDevelopmentFundService.putMoneyOnTheAccount(deposit.getDepositAmount());

        accountingEntriesRepository.save(accountingEntries);
    }

    private void transferFromInterestAccToPersonalAcc(Deposit deposit) {
        AccountingEntries accountingEntries = new AccountingEntries();

        accountingEntries.setProfile_id(deposit.getProfileId());
        accountingEntries.setAmount(depositsAccountsService.getInterest(deposit.getId())); //get all money
        accountingEntries.setDebet(chartOfAccountsRepository.getAccountNumberByAccountCode("INTEREST_ACCOUNT_FOR_" + deposit.getDepositType().getCode()));
        accountingEntries.setCredit(chartOfAccountsRepository.getAccountNumberByAccountCode("CURRENT_ACCOUNT"));

        clientAccountsService.putMoneyFromAccount(accountingEntries.getAmount(), deposit.getProfileId());

        accountingEntriesRepository.save(accountingEntries);
    }

    private void transferFromBankDevFundToDepositAcc(Deposit deposit) {
        AccountingEntries accountingEntries = new AccountingEntries();

        accountingEntries.setProfile_id(deposit.getProfileId());
        accountingEntries.setAmount(deposit.getDepositAmount());
        accountingEntries.setDebet(chartOfAccountsRepository.getAccountNumberByAccountCode("BANK_DEVELOPMENT_FUND_ACCOUNT"));
        accountingEntries.setCredit(chartOfAccountsRepository.getAccountNumberByAccountCode("ACCOUNT_FOR_" + deposit.getDepositType().getCode()));

        depositsAccountsService.putMoneyToMaster(deposit);
        bankDevelopmentFundService.getMoneyFromTheAccount(deposit.getDepositAmount());

        accountingEntriesRepository.save(accountingEntries);
    }

    @Override
    public void chargeInterestOnDeposits(Deposit deposit) {
        AccountingEntries accountingEntries = new AccountingEntries();

        accountingEntries.setProfile_id(deposit.getProfileId());
        accountingEntries.setDebet(chartOfAccountsRepository.getAccountNumberByAccountCode("BANK_DEVELOPMENT_FUND_ACCOUNT"));
        accountingEntries.setCredit(chartOfAccountsRepository.getAccountNumberByAccountCode("INTEREST_ACCOUNT_FOR_" + deposit.getDepositType().getCode()));
        accountingEntries.setAmount(depositService.getDailyCharge(deposit));

        bankDevelopmentFundService.getMoneyFromTheAccount(depositService.getDailyCharge(deposit));
        depositsAccountsService.putInterest(deposit.getId(), depositService.getDailyCharge(deposit));

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


//- - - - - - - - - - - - - - - C R E D I T - - - - - - - - - - - -

    @Override
    public void startCreditProgram(Credit credit) {
        transferFromBankDevFundToPersonalAcc(credit);
    }

    private void transferFromBankDevFundToPersonalAcc(Credit credit) {
        AccountingEntries accountingEntries = new AccountingEntries();

        accountingEntries.setProfile_id(credit.getProfileId());
        accountingEntries.setDebet(chartOfAccountsRepository.getAccountNumberByAccountCode("BANK_DEVELOPMENT_FUND_ACCOUNT"));
        accountingEntries.setCredit(chartOfAccountsRepository.getAccountNumberByAccountCode("CURRENT_ACCOUNT"));
        accountingEntries.setAmount(credit.getCreditAmount());

        bankDevelopmentFundService.getMoneyFromTheAccount(credit.getCreditAmount());
        clientAccountsService.putMoneyFromAccount(credit.getCreditAmount(), credit.getProfileId());

        accountingEntriesRepository.save(accountingEntries);
    }

    @Override
    public void chargePaymentsOnCredit(Credit credit) {
        chargeMainDebtOnCredit(credit);
        chargeInterestDebt(credit);

    }

    private void chargeMainDebtOnCredit(Credit credit) {
        AccountingEntries accountingEntries = new AccountingEntries();

        accountingEntries.setProfile_id(credit.getProfileId());
        accountingEntries.setDebet(" ");
        accountingEntries.setCredit(chartOfAccountsRepository.getAccountNumberByAccountCode("CREDIT_ACCOUNT") + "/" + chartOfAccountsRepository.getAccountNumberByAccountCode("BANK_DEVELOPMENT_FUND_ACCOUNT"));
        accountingEntries.setAmount(creditService.getMainDebt(credit));

        creditsAccountsService.getMoneyOnMasterAccount(credit, accountingEntries.getAmount());
        bankDevelopmentFundService.putMoneyOnTheAccount(accountingEntries.getAmount());

        accountingEntriesRepository.save(accountingEntries);
    }

    private void chargeInterestDebt(Credit credit) {
        AccountingEntries accountingEntries = new AccountingEntries();

        accountingEntries.setProfile_id(credit.getProfileId());
        accountingEntries.setDebet(" ");
        accountingEntries.setCredit(chartOfAccountsRepository.getAccountNumberByAccountCode("INTEREST_CREDIT_ACCOUNT") + "/" + chartOfAccountsRepository.getAccountNumberByAccountCode("BANK_DEVELOPMENT_FUND_ACCOUNT"));
        accountingEntries.setAmount(creditService.getInterestDebt(credit));

        creditsAccountsService.getMoneyOnInterestAccount(credit, accountingEntries.getAmount());
        bankDevelopmentFundService.putMoneyOnTheAccount(accountingEntries.getAmount());

        accountingEntriesRepository.save(accountingEntries);
    }

}
