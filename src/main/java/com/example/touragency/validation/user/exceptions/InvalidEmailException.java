package com.example.touragency.validation.user.exceptions;


public class InvalidEmailException extends InvalidDataException {


    public String getEmail(){
        return super.getData();
    }


    public InvalidEmailException(String message, String data) {
        super(message, data);
    }


}
