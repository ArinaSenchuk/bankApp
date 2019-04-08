package com.senchuk.project.service.impl;

import com.senchuk.project.model.Deposit;
import com.senchuk.project.repository.DepositRepository;
import com.senchuk.project.repository.UserRepository;
import com.senchuk.project.service.DepositService;
import com.senchuk.project.service.ProgramInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@Service(value="depositService")
public class DepositServiceImpl implements DepositService {

    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityHelper securityHelper;
    @Autowired
    private ProgramInterestService programInterestService;

    @Override
    public Deposit saveDeposit(Deposit deposit) {

        deposit.setProfile_id(userRepository.getProfileIdByLogin(securityHelper.getCurrentUsername()));
        deposit.setDateOfDepositEnd(deposit.getDateOfDepositStart().plusMonths(Integer.parseInt(deposit.getDepositTerm().getCode())));
        deposit.setDailyCharge(getDailyCharge(deposit));
        return depositRepository.save(deposit);
    }


    private String getDailyCharge(Deposit deposit) {
       double interest = Double.parseDouble((programInterestService.getValueOfInterest(deposit.getDepositTerm().getId(), deposit.getDepositType().getId())));

       double yearAmount = Double.parseDouble(deposit.getDepositAmount()) * interest / 100;

       double dailyAmount = yearAmount / 365;

       return Double.toString(dailyAmount);
    }
}


