package com.example.touragency.validation.user.exceptions;

public class InvalidDataException extends Exception{

    private String data;

    public InvalidDataException(String message, String data){
        super(message);
        this.data = data;
    }

    public String getData() {
        return data;
    }


}
