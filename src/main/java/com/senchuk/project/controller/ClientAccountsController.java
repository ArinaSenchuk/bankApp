package com.senchuk.project.controller;

import com.senchuk.project.model.ClientAccounts;
import com.senchuk.project.service.AccountingEntriesService;
import com.senchuk.project.service.ClientAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/client-account", produces = "application/json")
public class ClientAccountsController {

    @Autowired
    private ClientAccountsService clientAccountsService;

    @Autowired
    private AccountingEntriesService accountingEntriesService;

    @GetMapping(value = "/current")
    public ClientAccounts getCurrentClientAccount(){
        return clientAccountsService.getCurrentClientAccount();
    }

    @PostMapping(value = "/put")
    public void putMoneyFromAccount(@RequestBody String amount) {
        accountingEntriesService.putMoneyOnCashbox(amount);
        clientAccountsService.putMoneyFromAccount(amount);
    }
    @PostMapping(value = "/get")
    public boolean getMoneyFromAccount(@RequestBody String amount) {
        if(clientAccountsService.checkBalance(amount)) {
            accountingEntriesService.withdrawMoney(amount);
            clientAccountsService.getMoneyFromAccount(amount);
            return true;
        } else {
            return false;
        }
    }
}
