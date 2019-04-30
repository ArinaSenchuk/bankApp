package com.senchuk.project.service.impl;

import com.senchuk.project.model.ClientAccounts;
import com.senchuk.project.model.Credit;
import com.senchuk.project.model.Deposit;
import com.senchuk.project.model.Profile;
import com.senchuk.project.repository.ProfileRepository;
import com.senchuk.project.repository.UserRepository;
import com.senchuk.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service(value = "profileService")
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityHelper securityHelper;
    @Autowired
    private CreditService creditService;
    @Autowired
    private DepositService depositService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClientAccountsService clientAccountsService;
    @Autowired
    private AccountingEntriesService accountingEntriesService;

    @Override
    public Profile saveProfile(Profile profile) {
        if (profileRepository.isExists(profile.getIdentificationNumber(), profile.getPassportNumber()).isEmpty()) {
            return profileRepository.save(profile);
        } else
            throw new IllegalStateException("User with this identification number or passport number is already exist");
    }

    @Override
    public Profile saveProfileChanges(Profile profile) {
        List<Long> ids = profileRepository.isExists(profile.getIdentificationNumber(), profile.getPassportNumber());
        if (ids.size() == 0 || (ids.size() < 2 && ids.contains(profile.getId()))) {
            return profileRepository.save(profile);
        } else
            throw new IllegalStateException("User with this identification number or passport number is already exist");
    }

    @Override
    public Profile getProfile() {
        long id = userRepository.getProfileIdByLogin(securityHelper.getCurrentUsername());
        return profileRepository.findById(id);
    }

    @Override
    @Transactional
    public boolean deleteProfile() {
        List<Credit> allCredits = creditService.getAllCreditsByProfileId(userRepository.getProfileIdByLogin(securityHelper.getCurrentUsername()));
        List<Deposit> allDeposits = depositService.getAllDepositsOfCurrentUser();
        ClientAccounts clientAccountBalance = clientAccountsService.getCurrentClientAccount();
        if(allCredits.isEmpty() & allDeposits.isEmpty() &
                Double.parseDouble(clientAccountBalance.getAccountBalance()) >= 0){

            long profileId = userRepository.getProfileIdByLogin(securityHelper.getCurrentUsername());

            clientAccountsService.deleteAccount(profileId);
            userService.deleteUser(profileId);
            accountingEntriesService.deleteEntriesByProfileId(profileId);
            profileRepository.deleteById(profileId);
            return true;
        } else {
            return false;
        }

    }
}
