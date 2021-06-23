package com.example.touragency.validation.tour.exceptions;

import com.example.touragency.validation.InvalidDataException;

public class InvalidTicketsException extends InvalidDataException {



    private final String tickets;
    public InvalidTicketsException(String message, String data) {
        super(message, data);
        this.tickets = data;

    }

    public String getTickets() {
        return tickets;
    }
}
