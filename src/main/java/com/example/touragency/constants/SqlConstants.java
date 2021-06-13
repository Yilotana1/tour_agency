package com.example.touragency.constants;



public interface SqlConstants {

    //User
    String SQL_FIND_USER_BY_ID = "SELECT * FROM " + Tables.USER + " WHERE " + Fields.USER_ID + "=?;";

    String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM " + Tables.USER + " WHERE " + Fields.USER_LOGIN + "=?;";

    String SQL_FIND_ALL_USERS = "SELECT * FROM " + Tables.USER + ";";

    String SQL_INSERT_USER = "INSERT INTO " + Tables.USER +"("
            + Fields.USER_LOGIN + ", " + Fields.USER_PASSWORD + ", " + Fields.USER_FIRSTNAME + ", "
            + Fields.USER_LASTNAME + ", " + Fields.USER_EMAIL + ", " + Fields.USER_PHONE + ", "
            + Fields.USER_ROLE_ID + ", " + Fields.USER_STATUS_ID
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    String SQL_UPDATE_USER = "UPDATE " + Tables.USER + " SET "
            + Fields.USER_LOGIN + "=?, " + Fields.USER_PASSWORD + "=?, " + Fields.USER_FIRSTNAME + "=?, "
            + Fields.USER_LASTNAME + "=?, " + Fields.USER_EMAIL + "=?," + Fields.USER_PHONE + "=?, "
            + Fields.USER_ROLE_ID + "=?, " + Fields.USER_STATUS_ID + "=? WHERE " + Fields.ID + "=?;";


    String SQL_DELETE_USER = "DELETE FROM " + Tables.USER + " WHERE " + Fields.ID + "=?;";





    //Tour
    String SQL_FIND_TOUR_BY_ID = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID + " WHERE " + Fields.TOUR_ID + "=?";



    String SQL_FIND_TOUR_BY_NAME = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID + " WHERE " + Fields.TOUR_NAME + "=?";

    String SQL_FIND_TOURS_BY_CATEGORY = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID + " WHERE " + Fields.TOUR_CATEGORY_ID + "=?";

    String SQL_FIND_TOURS_BY_PRICE = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID + " WHERE " + Fields.TOUR_PRICE + "=?";

    String SQL_FIND_TOURS_BY_PEOPLE = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID + " WHERE " + Fields.TOUR_MAX_TICKETS + "=?";

    String SQL_FIND_TOURS_BY_HOTEL_ID = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID + " WHERE " + Fields.HOTEL_ID + "=?";

    String SQL_FIND_TOURS_BY_ORDER_ID = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.ORDER_HAS_TOUR + " ON " +
            Fields.TOUR_ID + " = " + Fields.ORDER_HAS_TOUR_TOUR_ID + " JOIN " + Tables.HOTEL + " ON " +
            Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID + " WHERE " + Fields.ORDER_HAS_TOUR_ORDER_ID + "=?;";



    String SQL_FIND_ALL_TOURS = "SELECT * FROM " + Tables.TOUR + " JOIN " + Tables.HOTEL + " ON "
            + Fields.TOUR_HOTEL_ID + " = " + Fields.HOTEL_ID;


    String SQL_INSERT_TOUR = "INSERT INTO " + Tables.TOUR +  "("
            + Fields.TOUR_NAME + ", " + Fields.TOUR_COUNTRY + ", " +  Fields.TOUR_PRICE + ", " + Fields.TOUR_MAX_TICKETS + ", "
            + Fields.TOUR_MIN_TICKETS + ", " + Fields.TOUR_TAKEN_TICKETS + ", " + Fields.TOUR_START_DATE + ", "
            + Fields.TOUR_END_DATE + ", " + Fields.TOUR_CATEGORY_ID + ", " + Fields.TOUR_STATUS_ID + ", "
            + Fields.TOUR_HOTEL_ID + ", " + Fields.TOUR_CITY
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    String SQL_UPDATE_TOUR = "UPDATE " + Tables.TOUR + " SET "
            + Fields.TOUR_NAME + "=?, " + Fields.TOUR_COUNTRY + "=?, " + Fields.TOUR_PRICE + "=?, "
            + Fields.TOUR_MAX_TICKETS + "=?, " + Fields.TOUR_MIN_TICKETS + "=?, " + Fields.TOUR_TAKEN_TICKETS
            + "=?, " + Fields.TOUR_START_DATE + "=?, " + Fields.TOUR_END_DATE + "=?, " + Fields.TOUR_CATEGORY_ID +
            "=?, " + Fields.TOUR_STATUS_ID + "=?, " + Fields.TOUR_HOTEL_ID + "=?, "
            + Fields.TOUR_CITY + "=?" + " WHERE " + Fields.ID + "=?;";

    String SQL_DELETE_TOUR = "DELETE FROM " + Tables.TOUR + " WHERE " + Fields.TOUR_ID + "=?;";






    //Order
    String SQL_FIND_ORDER_BY_ID = "SELECT * FROM " + Tables.ORDER + " JOIN " + Tables.USER + " ON "
            + Fields.ORDER_CLIENT_ID + " = " + Fields.USER_ID
            + " WHERE " + Fields.ORDER_ID + "=?;";


    String SQL_FIND_ALL_ORDERS = "SELECT * FROM " + Tables.ORDER + " JOIN " + Tables.USER + " ON "
            + Fields.ORDER_CLIENT_ID + " = " + Fields.USER_ID;

    String SQL_INSERT_ORDER = "INSERT INTO " + Tables.ORDER + "("
            + Fields.ORDER_DATE + ", " + Fields.ORDER_STATUS_ID + ", " + Fields.ORDER_CLIENT_ID + ", "
            + Fields.ORDER_PRICE +
            ", "  + Fields.ORDER_TOUR_COUNT
            + ") VALUES (?, ?, ?, ?, ?);";

    String SQL_UPDATE_ORDER = "UPDATE " + Tables.ORDER +  " SET "
            + Fields.ORDER_DATE + "=?, " + Fields.ORDER_STATUS_ID + "=?, "
            + Fields.ORDER_PRICE + "=?, " +  Fields.ORDER_TOUR_COUNT + "=?" + " WHERE " + Fields.ORDER_ID + "=?";

    String SQL_DELETE_ORDER = "DELETE FROM " + Tables.ORDER + " WHERE " + Fields.ORDER_ID + "=?;";





    //Hotel
    String SQL_FIND_HOTEL_BY_ID = "SELECT * FROM " + Tables.HOTEL + " WHERE " + Fields.HOTEL_ID + "=?";

    String SQL_FIND_ALL_HOTELS = "SELECT * FROM " + Tables.HOTEL;

    String SQL_INSERT_HOTEL = "INSERT INTO " + Tables.HOTEL + "("
            + Fields.HOTEL_NAME + ", " + Fields.HOTEL_CITY + ", " +  Fields.HOTEL_ADDRESS
            + ", " + Fields.HOTEL_STARS + ", " + Fields.HOTEL_ADMIN_PHONE
            + ") VALUES (?, ?, ?, ?, ?);";


    String SQL_UPDATE_HOTEL = "UPDATE " + Tables.HOTEL + " SET "
            + Fields.HOTEL_NAME + "=?, " + Fields.HOTEL_CITY + "=?, " + Fields.HOTEL_ADDRESS + "=?, "
            + Fields.HOTEL_STARS + "=?, " + Fields.HOTEL_ADMIN_PHONE + "=?" + " WHERE " + Fields.HOTEL_ID + "=?;";

    String SQL_DELETE_HOTEL = "DELETE FROM " + Tables.HOTEL + " WHERE " + Fields.HOTEL_ID + "=?;";




    //Discount
    String SQL_FIND_DISCOUNT_BY_ID = "SELECT * FROM " + Tables.DISCOUNT + " WHERE " + Fields.DISCOUNT_ID + "=?";

    String SQL_FIND_ALL_DISCOUNTS = "SELECT * FROM " + Tables.DISCOUNT;

    String SQL_INSERT_DISCOUNT = "INSERT INTO " + Tables.DISCOUNT + "("
            + Fields.DISCOUNT_PERCENT + ", " + Fields.DISCOUNT_MAX_PERCENT
            +") VALUES (?, ?);";


    String SQL_UPDATE_DISCOUNT = "UPDATE " + Tables.DISCOUNT + " SET "
            + Fields.DISCOUNT_PERCENT + "=?, " + Fields.DISCOUNT_MAX_PERCENT + "=? WHERE " + Fields.DISCOUNT_ID + "=?;";

    String SQL_DELETE_DISCOUNT = "DELETE FROM " + Tables.DISCOUNT + " WHERE " + Fields.DISCOUNT_ID + "=?;";



    //TourHasOrder

    String SQL_ADD_TOUR_TO_ORDER = "INSERTINTO " + Tables.ORDER_HAS_TOUR +
            " (" + Fields.ORDER_HAS_TOUR_ORDER_ID + ", " + Fields.ORDER_HAS_TOUR_TOUR_ID + ")"
            + "VALUES (?, ?)";

}
