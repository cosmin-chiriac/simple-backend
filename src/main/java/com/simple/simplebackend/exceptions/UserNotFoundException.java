package com.simple.simplebackend.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super("Could not find user " + id);
    }

}
