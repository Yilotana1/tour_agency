package com.example.touragency.validation.user.exceptions;


public class InvalidPasswordException extends InvalidDataException {


    public String getPassword(){
        return super.getData();
    }

    public InvalidPasswordException(String message, String data) {
        super(message, data);
    }
}
