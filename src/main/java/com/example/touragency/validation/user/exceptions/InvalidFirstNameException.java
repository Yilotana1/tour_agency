package com.example.touragency.validation.user.exceptions;


import com.example.touragency.validation.InvalidDataException;

public class InvalidFirstNameException extends InvalidDataException {


    public String getFirstName(){
        return super.getData();
    }


    public InvalidFirstNameException(String message, String data) {
        super(message, data);
    }
}
