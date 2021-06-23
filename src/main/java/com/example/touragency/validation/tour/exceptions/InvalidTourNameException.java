package com.example.touragency.validation.tour.exceptions;

import com.example.touragency.validation.InvalidDataException;

public class InvalidTourNameException extends InvalidDataException {

    private final String tourName;

    public String getTourName() {
        return tourName;
    }

    public InvalidTourNameException(String message, String data) {
        super(message, data);
        this.tourName = data;
    }
}
