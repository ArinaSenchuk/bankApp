package com.senchuk.project.controller;

import com.senchuk.project.model.User;
import com.senchuk.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @PostMapping
    public User save(@RequestBody User user){
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable(value = "id") long id) {
        userRepository.deleteById(id);
        return 1;
    }

}
