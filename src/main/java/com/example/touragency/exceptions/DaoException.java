package com.example.touragency.exceptions;

import java.sql.SQLException;

public class DaoException extends SQLException {

    public DaoException(String message){
        super(message);
    }
}
