package com.senchuk.project.service.impl;

import com.senchuk.project.repository.ChartOfAccountsRepository;
import com.senchuk.project.service.ChartOfAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "chartOfAccountService")
public class ChartOfAccountsServiceImpl implements ChartOfAccountsService {

    @Autowired
    private ChartOfAccountsRepository chartOfAccountsRepository;


    @Override
    public String getAccountNumberByAccountCode(String accountCode) {

        return chartOfAccountsRepository.getAccountNumberByAccountCode(accountCode);
    }
}
