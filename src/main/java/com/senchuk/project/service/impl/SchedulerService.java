package com.senchuk.project.service.impl;

import com.senchuk.project.model.Credit;
import com.senchuk.project.model.Deposit;
import com.senchuk.project.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
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
    @Autowired
    private CreditService creditService;

    @Scheduled(cron = "0 14 00 * * ?")
    public void startDepositsProgram() {  //Start in day of start:) and end at the end day

        List<Deposit> allDeposits = depositService.getDeposits();

        if (!allDeposits.isEmpty()) {
            for (Deposit deposit : allDeposits) {
                LocalDate currentDate = LocalDate.now();
                if(currentDate.equals(deposit.getDateOfDepositStart())){
                    accountingEntriesService.startDepositProgram(deposit);
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
                    if (currentDate.isAfter(deposit.getDateOfDepositStart()) & !currentDate.equals(deposit.getDateOfDepositStart())) {
                    accountingEntriesService.chargeInterestOnDeposits(deposit);
                }
                    //  ЗАКРЫТИЕ ДЕПОЗИТА
                    if(currentDate.equals(deposit.getDateOfDepositEnd())) {
                        accountingEntriesService.endDepositProgram(deposit);
                    }
            }
        }
    }

    @Scheduled(cron = "0 56 16 * * ?")
    public void startCreditProgram() {
        List<Credit> allCredits = creditService.getAllCredits();

        LocalDate currentDate = LocalDate.now();

        if(!allCredits.isEmpty()) {
            for (Credit credit: allCredits) {
                if (currentDate.equals(credit.getDateOfCreditStart())) {
                    accountingEntriesService.startCreditProgram(credit);
                }
            }
        }
    }

    @Scheduled(cron = "0 56 16 * * ?")
    public void finishCreditProgram() {
        List<Credit> allCredits = creditService.getAllCredits();

        LocalDate currentDate = LocalDate.now();

        if(!allCredits.isEmpty()) {
            for (Credit credit: allCredits) {
                if (currentDate.equals(credit.getDateOfCreditEnd()) ||
                currentDate.isAfter(credit.getDateOfCreditEnd())) {
                    accountingEntriesService.finishCreditProgram(credit.getId());
                }
            }
        }

    }


    @Scheduled(cron = "0 18 17 * * ?")
    public void chargeInterestOnCredits() {
        List<Credit> allCredits = creditService.getAllCredits();

        LocalDate currentDate = LocalDate.now();

        YearMonth daysInTheMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonth());
        int days = daysInTheMonth.lengthOfMonth();

        if(!allCredits.isEmpty()) {
            for (Credit credit: allCredits) {
                if (currentDate != credit.getDateOfCreditStart()) {
                    if (currentDate.getDayOfMonth() == credit.getDateOfCreditStart().getDayOfMonth()) {

                        accountingEntriesService.chargePaymentsOnCredit(credit);

                    } else if (currentDate.getDayOfMonth() == days &
                            credit.getDateOfCreditStart().getDayOfMonth() > days) {

                        accountingEntriesService.chargePaymentsOnCredit(credit);
                    }

                }
            }
        }
    }

}
