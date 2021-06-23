package com.example.touragency.exceptions;

import java.sql.SQLException;

public class ServiceException extends Exception {

    public ServiceException(String message){
        super(message);
    }
}
