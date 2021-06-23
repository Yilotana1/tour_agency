package com.example.touragency.validation.tour.exceptions;

import com.example.touragency.validation.InvalidDataException;

public class InvalidDateException extends InvalidDataException {

    private String date;

    public InvalidDateException(String message, String data) {
        super(message, data);
        this.date = data;
    }

    public String getDate() {
        return date;
    }
}
