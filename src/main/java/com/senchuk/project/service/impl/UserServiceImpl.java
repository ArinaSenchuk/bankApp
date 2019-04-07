package com.senchuk.project.service.impl;

import com.senchuk.project.model.User;
import com.senchuk.project.repository.UserRepository;
import com.senchuk.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public void saveUser(User user, Long profileId) {
        user.setProfile_id(profileId);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        this.userRepository.save(user);
    }
}
