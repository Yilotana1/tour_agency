package com.example.touragency.validation.user.exceptions;

import com.example.touragency.validation.InvalidDataException;

public class InvalidLoginException extends InvalidDataException {


    public String getLogin(){
        return super.getData();
    }



    public InvalidLoginException(String message, String data) {
        super(message, data);
    }
}