package com.senchuk.project.service.impl;

import com.senchuk.project.model.BankDevelopmentFund;
import com.senchuk.project.repository.BankDevelopmentFundRepository;
import com.senchuk.project.service.BankDevelopmentFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service(value ="bankDevelopmentFundService")
public class BankDevelopmentServiceImpl implements BankDevelopmentFundService {

    @Autowired
    private BankDevelopmentFundRepository bankDevelopmentFundRepository;

    @Override
    public void putMoneyOnTheAccount(String amount) {
        double balance = Double.parseDouble(bankDevelopmentFundRepository.getBalanceOfBankDevelopmentFund());

        BankDevelopmentFund bankDevelopmentFund = new BankDevelopmentFund();
        balance = balance + Double.parseDouble(amount);

        bankDevelopmentFund.setId(1);
        bankDevelopmentFund.setBalance(Double.toString(balance));

        bankDevelopmentFundRepository.save(bankDevelopmentFund);

    }

    @Override
    public void getMoneyFromTheAccount(String amount) {
        double balance = Double.parseDouble(bankDevelopmentFundRepository.getBalanceOfBankDevelopmentFund());

        BankDevelopmentFund bankDevelopmentFund = new BankDevelopmentFund();
        balance = balance-Double.parseDouble(amount);

        bankDevelopmentFund.setId(1);
        bankDevelopmentFund.setBalance(Double.toString(balance));

        bankDevelopmentFundRepository.save(bankDevelopmentFund);

    }
}
