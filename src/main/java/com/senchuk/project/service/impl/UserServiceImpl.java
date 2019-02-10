package com.senchuk.project.service.impl;


import com.senchuk.project.model.User;
import com.senchuk.project.repository.UserRepository;
import com.senchuk.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUserData(User user) {
        if (userRepository.isExists(user.getPassportNumber(), user.getIdentificationNumber())==null) {
            return userRepository.save(user);
        }
        else throw new IllegalStateException("User with this passportNumber or identificationNumber is already exist");
    }
}
