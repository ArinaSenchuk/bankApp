package com.senchuk.project.service.impl;

import com.senchuk.project.model.Credit;
import com.senchuk.project.repository.CreditRepository;
import com.senchuk.project.repository.UserRepository;
import com.senchuk.project.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value="creditService")
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityHelper securityHelper;

    @Override
    public Credit save(Credit credit) {

        credit.setProfile_id(userRepository.getProfileIdByLogin(securityHelper.getCurrentUsername()));
        credit.setDateOfCreditEnd(credit.getDateOfCreditStart().plusYears(Integer.parseInt(credit.getCreditTerm().getCode())));
        return creditRepository.save(credit);
    }
}


