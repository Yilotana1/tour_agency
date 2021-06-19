package com.example.touragency.validation.user.exceptions;


public class InvalidPhoneException extends InvalidDataException {


    public String getPhone(){
        return super.getData();
    }

    public InvalidPhoneException(String message, String data) {
        super(message, data);
    }
}
