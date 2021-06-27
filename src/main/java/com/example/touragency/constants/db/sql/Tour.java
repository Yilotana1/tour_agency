package com.example.touragency.constants.db.sql;

import com.example.touragency.constants.db.Fields;
import com.example.touragency.constants.db.Tables;

public interface Tour {

    String SQL_FIND_TOUR_BY_ID = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID + " WHERE " + Fields.TOUR_ID + "=?";

    String SQL_FIND_TOUR_BY_NAME = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID + " WHERE " + Fields.TOUR_NAME + "=?";

    String SQL_FIND_ALL_TOURS = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID;

    String SQL_FIND_TOURS_BY_LIMIT = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID + " LIMIT ?, ?";

    String SQL_FIND_TOURS_BY_LIMIT_BURNING_FIRST = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID + " ORDER BY " + Fields.TOUR_STATUS_ID + " LIMIT ?, ?";

    String SQL_FIND_TOURS_BY_LIMIT_NON_BURNING_FIRST = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID + " ORDER BY " + Fields.TOUR_STATUS_ID + " DESC LIMIT ?, ?";

    String SQL_FIND_TOURS_BY_LIMIT_HIGH_HOTEL_STARS_FIRST = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID + " ORDER BY " + Fields.HOTEL_STARS + " DESC LIMIT ?, ?";

    String SQL_FIND_TOURS_BY_LIMIT_LOW_HOTEL_STARS_FIRST = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID + " ORDER BY " + Fields.HOTEL_STARS + " LIMIT ?, ?";

    String SQL_FIND_TOURS_BY_LIMIT_HIGH_PRICE_FIRST = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID + " ORDER BY " + Fields.TOUR_PRICE + " DESC LIMIT ?, ?";

    String SQL_FIND_TOURS_BY_LIMIT_LOW_PRICE_FIRST = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID + " ORDER BY " + Fields.TOUR_PRICE + " LIMIT ?, ?";

    String SQL_FIND_TOURS_BY_LIMIT_EXCURSION = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID
            + " WHERE " + Fields.TOUR_CATEGORY_ID + " = ? LIMIT ?, ?";

    String SQL_FIND_TOURS_BY_LIMIT_REST = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID
            + " WHERE " + Fields.TOUR_CATEGORY_ID + " = ? LIMIT ?, ?";

    String SQL_FIND_TOURS_BY_LIMIT_SHOPPING = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID
            + " WHERE " + Fields.TOUR_CATEGORY_ID + " = ? LIMIT ?, ?";


    String SQL_FIND_TOURS_BY_LIMIT_COUNTRY = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID
            + " WHERE " + Fields.TOUR_COUNTRY + " = ? LIMIT ?, ?";

    String SQL_FIND_TOURS_BY_LIMIT_NAME = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID
            + " WHERE " + Fields.TOUR_NAME + " = ? LIMIT ?, ?";

    String SQL_INSERT_TOUR = "INSERT INTO " + Tables.TOUR +  "("
            + Fields.TOUR_NAME + ", " + Fields.TOUR_COUNTRY + ", " +  Fields.TOUR_PRICE + ", " + Fields.TOUR_MAX_TICKETS + ", "
            + ", " + Fields.TOUR_TAKEN_TICKETS + ", " + Fields.TOUR_START_DATE + ", "
            + Fields.TOUR_END_DATE + ", " + Fields.TOUR_CATEGORY_ID + ", " + Fields.TOUR_STATUS_ID + ", "
            + Fields.TOUR_HOTEL_ID + ", " + Fields.TOUR_CITY
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    String SQL_UPDATE_TOUR = "UPDATE " + Tables.TOUR + " SET "
            + Fields.TOUR_NAME + "=?, " + Fields.TOUR_COUNTRY + "=?, " + Fields.TOUR_PRICE + "=?, "
            + Fields.TOUR_MAX_TICKETS + "=?, " + Fields.TOUR_TAKEN_TICKETS
            + "=?, " + Fields.TOUR_START_DATE + "=?, " + Fields.TOUR_END_DATE + "=?, " + Fields.TOUR_CATEGORY_ID +
            "=?, " + Fields.TOUR_STATUS_ID + "=?, " + Fields.TOUR_HOTEL_ID + "=?, "
            + Fields.TOUR_CITY + "=?" + " WHERE " + Fields.ID + "=?;";

    String SQL_FIND_TOUR_NUMBER_AS_COUNT = "SELECT COUNT(" + Fields.TOUR_ID + ") AS count FROM " + Tables.TOUR;

    String SQL_DELETE_TOUR = "DELETE FROM " + Tables.TOUR + " WHERE " + Fields.TOUR_ID + "=?;";
}
