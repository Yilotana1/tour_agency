package com.example.touragency.validation.tour.exceptions;

import com.example.touragency.validation.InvalidDataException;

public class InvalidPriceException extends InvalidDataException {

    private final String price;

    public InvalidPriceException(String message, String data) {
        super(message, data);
        this.price = data;
    }

    public String getPrice() {
        return price;
    }
}
