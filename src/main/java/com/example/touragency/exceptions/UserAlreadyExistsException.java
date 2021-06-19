package com.example.touragency.exceptions;

import com.example.touragency.model.entity.User;

public class UserAlreadyExistsException extends Exception{


    private User user;

    public UserAlreadyExistsException(String message, User user) {
        super(message);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
