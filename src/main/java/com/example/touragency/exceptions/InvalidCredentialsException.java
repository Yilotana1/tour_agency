package com.example.touragency.exceptions;

public class InvalidCredentialsException extends Exception{

    private String login;
    private String password;

    public InvalidCredentialsException(String message, String login, String password) {
        super(message);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
