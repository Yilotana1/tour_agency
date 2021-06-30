package com.example.touragency.constants.db.sql;

import com.example.touragency.constants.db.Fields;
import com.example.touragency.constants.db.Tables;

public interface Hotel {
    String SQL_FIND_HOTEL_BY_ID = "SELECT * FROM " + Tables.HOTEL + " WHERE " + Fields.HOTEL_ID + "=?";

    String SQL_FIND_HOTEL_BY_NAME = "SELECT * FROM " + Tables.HOTEL + " WHERE " + Fields.HOTEL_NAME + "=?";

    String SQL_FIND_ALL_HOTELS = "SELECT * FROM " + Tables.HOTEL;

    String SQL_FIND_HOTELS_BY_LIMIT = "SELECT * FROM " + Tables.HOTEL + " LIMIT ?, ?";


    String SQL_FIND_HOTEL_NUMBER_AS_COUNT = "SELECT COUNT(" + Fields.HOTEL_ID + ") AS count FROM " + Tables.HOTEL;

    String SQL_INSERT_HOTEL = "INSERT INTO " + Tables.HOTEL + "("
            + Fields.HOTEL_NAME + ", " + Fields.HOTEL_CITY + ", " +  Fields.HOTEL_ADDRESS
            + ", " + Fields.HOTEL_STARS + ", " + Fields.HOTEL_ADMIN_PHONE
            + ") VALUES (?, ?, ?, ?, ?);";


    String SQL_UPDATE_HOTEL = "UPDATE " + Tables.HOTEL + " SET "
            + Fields.HOTEL_NAME + "=?, " + Fields.HOTEL_CITY + "=?, " + Fields.HOTEL_ADDRESS + "=?, "
            + Fields.HOTEL_STARS + "=?, " + Fields.HOTEL_ADMIN_PHONE + "=?" + " WHERE " + Fields.HOTEL_ID + "=?;";

    String SQL_DELETE_HOTEL = "DELETE FROM " + Tables.HOTEL + " WHERE " + Fields.HOTEL_ID + "=?;";
}
