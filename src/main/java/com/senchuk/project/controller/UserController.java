package com.senchuk.project.controller;


import com.senchuk.project.model.User;
import com.senchuk.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/save")
    public User saveUser(@RequestBody User user) {
        return userService.saveUserData(user);
    }

}
