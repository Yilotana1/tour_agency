package com.example.touragency.model.exceptions;

import java.sql.SQLException;

public class ServiceException extends SQLException {

    public ServiceException(String message){
        super(message);
    }
}
