package com.simple.simplebackend.dao;

import com.simple.simplebackend.exceptions.UserNotFoundException;
import com.simple.simplebackend.model.User;
import com.simple.simplebackend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {
    @Autowired
    UserRepo userRepo;

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public User findById(int id) {
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User save(User user) {
        return userRepo.save(user);
    }
}
