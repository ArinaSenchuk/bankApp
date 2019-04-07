package com.senchuk.project.service.impl;

import com.senchuk.project.model.Deposit;
import com.senchuk.project.repository.DepositRepository;
import com.senchuk.project.repository.UserRepository;
import com.senchuk.project.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="depositService")
public class DepositServiceImpl implements DepositService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityHelper securityHelper;

    @Override
    public Deposit saveDeposit(Deposit deposit) {

        deposit.setProfile_id(userRepository.getProfileIdByLogin(securityHelper.getCurrentUsername()));
        return depositRepository.save(deposit);
    }

}


