package com.example.touragency.validation.tour.exceptions;

import com.example.touragency.validation.InvalidDataException;

public class InvalidCountryException extends InvalidDataException {


    private final String country;
    public InvalidCountryException(String message, String data) {
        super(message, data);
        this.country = data;
    }

    public String getCountry() {
        return country;
    }
}
