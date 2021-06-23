package com.example.touragency.validation.tour.exceptions;

import com.example.touragency.validation.InvalidDataException;

public class InvalidCityException extends InvalidDataException {

    private final String city;

    public InvalidCityException(String message, String data) {
        super(message, data);
        this.city = data;
    }

    public String getCity() {
        return city;
    }
}
