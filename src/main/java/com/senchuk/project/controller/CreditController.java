package com.senchuk.project.controller;

import com.senchuk.project.model.Credit;
import com.senchuk.project.model.Deposit;
import com.senchuk.project.model.dto.CreditDto;
import com.senchuk.project.service.AccountingEntriesService;
import com.senchuk.project.service.CreditService;
import com.senchuk.project.service.CreditsAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/credits", produces = "application/json")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @Autowired
    private CreditsAccountsService creditsAccountsService;

    @Autowired
    private AccountingEntriesService accountingEntriesService;

    @PostMapping(value = "/save")
    public void saveCredit(@RequestBody Credit credit){

        creditService.save(credit);
        creditsAccountsService.createCreditAccounts(credit);
    }

    @GetMapping
    public List<CreditDto> getAllCreditInformation() {
        return creditService.getAllCreditsOfCurrentUser();
    }

    @PostMapping(value = "/payment")
    public boolean payDebt(@RequestParam(value = "creditId") long creditId){
        return accountingEntriesService.payCreditDebt(creditId);
    }


}
