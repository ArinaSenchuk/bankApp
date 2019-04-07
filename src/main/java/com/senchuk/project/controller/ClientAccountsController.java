package com.senchuk.project.controller;

import com.senchuk.project.model.ClientAccounts;
import com.senchuk.project.service.ClientAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/client-account", produces = "application/json")
public class ClientAccountsController {

    @Autowired
    private ClientAccountsService clientAccountsService;

    @GetMapping(value = "/current")
    public ClientAccounts getCurrentClientAccount(){
        return clientAccountsService.getCurrentClientAccount();
    }
}
