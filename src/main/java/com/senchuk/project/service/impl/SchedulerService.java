package com.senchuk.project.service.impl;

import com.senchuk.project.model.Deposit;
import com.senchuk.project.service.AccountingEntriesService;
import com.senchuk.project.service.BankDevelopmentFundService;
import com.senchuk.project.service.DepositService;
import com.senchuk.project.service.DepositsAccountsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SchedulerService {

    @Autowired
    private DepositService depositService;
    @Autowired
    private BankDevelopmentFundService bankDevelopmentFundService;
    @Autowired
    private AccountingEntriesService accountingEntriesService;
    @Autowired
    private DepositsAccountsService depositsAccountsService;

    @Scheduled(cron = "0 14 00 * * ?")
    public void startEndDepositsProgram() {  //Start in day of start:) and end at the end day

        List<Deposit> allDeposits = depositService.getDeposits();

        if (!allDeposits.isEmpty()) {
            for (Deposit deposit : allDeposits) {
                LocalDate currentDate = LocalDate.now();
                if(currentDate.equals(deposit.getDateOfDepositStart())){
                    accountingEntriesService.startDepositProgram(deposit);
                }
                if (currentDate.equals(deposit.getDateOfDepositEnd())){
                    accountingEntriesService.endDepositProgram(deposit);
                }

            }
        }
    }


    @Scheduled(cron = "0 50 23 * * ?")
    public void chargeInterestOnDeposits() {
        List<Deposit> allDeposits = depositService.getDeposits();

        LocalDate currentDate = LocalDate.now();

        if(!allDeposits.isEmpty()) {
                for (Deposit deposit: allDeposits) {
                    if (currentDate.isAfter(deposit.getDateOfDepositStart()) || currentDate.equals(deposit.getDateOfDepositStart())) {
                    bankDevelopmentFundService.getMoneyFromTheAccount(depositService.getDailyCharge(deposit));
                    accountingEntriesService.chargeInterestOnDeposits(deposit, depositService.getDailyCharge(deposit));
                    depositsAccountsService.putInterest(deposit.getId(), depositService.getDailyCharge(deposit));
                }
            }

        }


    }
}
