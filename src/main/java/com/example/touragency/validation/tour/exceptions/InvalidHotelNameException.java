package com.example.touragency.validation.tour.exceptions;

import com.example.touragency.validation.InvalidDataException;

public class InvalidHotelNameException extends InvalidDataException {

    private final String hotel;
    public InvalidHotelNameException(String message, String data) {
        super(message, data);
        this.hotel = data;
    }

    public String getHotel() {
        return hotel;
    }
}
