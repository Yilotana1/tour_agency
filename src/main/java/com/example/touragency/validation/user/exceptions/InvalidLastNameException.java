package com.example.touragency.validation.user.exceptions;


public class InvalidLastNameException extends InvalidDataException {



    public String getLastName(){
        return super.getData();
    }

    public InvalidLastNameException(String message, String data) {
        super(message, data);
    }
}
