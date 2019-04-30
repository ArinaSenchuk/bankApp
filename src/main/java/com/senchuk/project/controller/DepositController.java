package com.senchuk.project.controller;

import com.senchuk.project.model.Deposit;
import com.senchuk.project.service.AccountingEntriesService;
import com.senchuk.project.service.ClientAccountsService;
import com.senchuk.project.service.DepositService;
import com.senchuk.project.service.DepositsAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/deposits", produces = "application/json")
public class DepositController {

    @Autowired
    private DepositService depositService;
    @Autowired
    private DepositsAccountsService depositsAccountsService;
    @Autowired
    private ClientAccountsService clientAccountsService;
    @Autowired
    private AccountingEntriesService accountingEntriesService;

    @PostMapping(value = "/save")
    public boolean saveDeposit(@RequestBody Deposit deposit){
        if(clientAccountsService.checkBalance(deposit.getDepositAmount())){
            depositService.saveDeposit(deposit);
            depositsAccountsService.createDepositAccounts(deposit);
            accountingEntriesService.transferFromPersonalAccToDepositAcc(deposit);
            return true;
        } else {
            return false;
        }
    }

    @GetMapping
    public List<Deposit> getAll() {
        return depositService.getAllDepositsOfCurrentUser();
    }
}
