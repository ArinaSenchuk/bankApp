package com.senchuk.project.controller;

import com.senchuk.project.model.Deposit;
import com.senchuk.project.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/deposits", produces = "application/json")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @PostMapping(value = "/save")
    public void saveDeposit(@RequestBody Deposit deposit){
        depositService.saveDeposit(deposit);
    }
}
