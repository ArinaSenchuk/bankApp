package com.senchuk.project.service;

import com.senchuk.project.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void delete(Long id);

    void saveUser(User user, Long profileId);

    void deleteUser(long profileId);
}
