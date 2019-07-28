package com.senchuk.project.service.impl;

import com.senchuk.project.model.Credit;
import com.senchuk.project.repository.CreditRepository;
import com.senchuk.project.repository.UserRepository;
import com.senchuk.project.service.CreditService;
import com.senchuk.project.service.InterestProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service(value="creditService")
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditRepository creditRepository;
    @Autowired
    private InterestProgramService interestProgramService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityHelper securityHelper;

    @Override
    public Credit save(Credit credit) {

        credit.setProfileId(userRepository.getProfileIdByLogin(securityHelper.getCurrentUsername()));
        credit.setDateOfCreditEnd(credit.getDateOfCreditStart().plusYears(Integer.parseInt(credit.getCreditTerm().getCode())));
        return creditRepository.save(credit);
    }

    @Override
    public List<Credit> getAllCredits() {
        return creditRepository.findAll();
    }

    @Override
    public List<Credit> getAllCreditsOfCurrentUser() {
        return creditRepository.findAllByProfileId(userRepository.getProfileIdByLogin(securityHelper.getCurrentUsername()));
    }

    @Override
    public String getMainDebt(Credit credit) {
        return Double.toString(Double.parseDouble(credit.getCreditAmount()) / Double.parseDouble(credit.getCreditTerm().getCode()) / 12);
    }

    @Override
    public String getInterestDebt(Credit credit) {

        if(credit.getCreditType().getCode().equals("CREDIT_WITH_EQUAL_PAYMENTS")) {
            //АННУИТЕТНЫЙ ПЛАТЕЖ
            double percent = Double.parseDouble(interestProgramService.getValueOfInterest(credit.getCreditTerm().getId(), credit.getCreditType().getId()));
            double yearAmount = Double.parseDouble(credit.getCreditAmount()) * percent / 100;
            return Double.toString(yearAmount / Double.parseDouble(credit.getCreditTerm().getCode()) / 12);

        } else {
            //ДИФФЕРЕНЦИРОВАННЫЙ ПЛАТЕЖ
            double mainDebt = Double.parseDouble(getMainDebt(credit));

            LocalDate currentDate = LocalDate.now();

            YearMonth yearMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonth());

            long monthBetween = ChronoUnit.MONTHS.between(credit.getDateOfCreditStart(),currentDate);

            double percent = Double.parseDouble(interestProgramService.getValueOfInterest(credit.getCreditTerm().getId(), credit.getCreditType().getId()));
            double interestDebt = (Double.parseDouble(credit.getCreditAmount()) - mainDebt*monthBetween) * percent / 100 / yearMonth.lengthOfYear() * yearMonth.lengthOfMonth();

            return Double.toString(interestDebt);
        }
    }
}


