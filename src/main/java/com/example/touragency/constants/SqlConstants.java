package com.example.touragency.constants;

public interface SqlConstants {

    //User
    String SQL_FIND_USER_BY_ID = "SELECT * FROM user WHERE " + Fields.ID + "=?;";

    String SQL_FIND_ALL_USERS = "SELECT * FROM user;";

    String SQL_INSERT_USER = "INSERT INTO user ("
            + Fields.USER_LOGIN + ", " + Fields.USER_PASSWORD + ", " + Fields.USER_FIRSTNAME + ", "
            + Fields.USER_LASTNAME + ", " + Fields.USER_EMAIL + ", " + Fields.USER_PHONE + ", "
            + Fields.USER_ROLE_ID + ", " + Fields.USER_STATUS_ID
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    String SQL_UPDATE_USER = "UPDATE user SET "
            + Fields.USER_LOGIN + "=?, " + Fields.USER_PASSWORD + "=?, " + Fields.USER_FIRSTNAME + "=?, "
            + Fields.USER_LASTNAME + "=?, " + Fields.USER_EMAIL + "=?," + Fields.USER_PHONE + "=?, "
            + Fields.USER_ROLE_ID + "=?, " + Fields.USER_STATUS_ID + "=? WHERE " + Fields.ID + "=?;";


    String SQL_DELETE_USER = "DELETE FROM user WHERE " + Fields.ID + "=?;";





    //Tour
    String SQL_FIND_TOUR_BY_ID = "SELECT * FROM tour WHERE " + Fields.ID + "=?";

    String SQL_FIND_TOUR_BY_CATEGORY = "SELECT * FROM tour WHERE " + Fields.TOUR_CATEGORY_ID + "=?";

    String SQL_FIND_TOUR_BY_PRICE = "SELECT * FROM tour WHERE " + Fields.TOUR_PRICE + "=?";

    String SQL_FIND_TOUR_BY_PEOPLE = "SELECT * FROM tour WHERE " + Fields.TOUR_MAX_PLACES + "=?";

    String SQL_FIND_TOUR_BY_HOTEL = "SELECT * FROM tour WHERE " + Fields.TOUR_HOTEL_ID + "=?";


    String SQL_FIND_ALL_TOUR = "SELECT * FROM tour";

    String SQL_INSERT_TOUR = "INSERT INTO tour ("
            + Fields.TOUR_NAME + ", " + Fields.TOUR_COUNTRY + ", " +  Fields.TOUR_PRICE + ", " + Fields.TOUR_MAX_PLACES + ", "
            + Fields.TOUR_MIN_PLACES + ", " + Fields.TOUR_TAKEN_PLACES + ", " + Fields.TOUR_START_DATE + ", "
            + Fields.TOUR_END_DATE + ", " + Fields.TOUR_CATEGORY_ID + ", " + Fields.TOUR_STATUS_ID + ", "
            + Fields.TOUR_HOTEL_ID + ", " + Fields.TOUR_CITY
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    String SQL_UPDATE_TOUR = "UPDATE tour SET "
            + Fields.TOUR_NAME + "=?, " + Fields.TOUR_COUNTRY + "=?, " + Fields.TOUR_PRICE + "=?, "
            + Fields.TOUR_MAX_PLACES + "=?, " + Fields.TOUR_MIN_PLACES + "=?, " + Fields.TOUR_TAKEN_PLACES
            + "=?, " + Fields.TOUR_START_DATE + "=?, " + Fields.TOUR_END_DATE + "=?, " + Fields.TOUR_CATEGORY_ID +
            "=?, " + Fields.TOUR_STATUS_ID + "=?, " + Fields.TOUR_HOTEL_ID + "=?, "
            + Fields.TOUR_CITY + "=?" + " WHERE " + Fields.ID + "=?;";

    String SQL_DELETE_TOUR = "DELETE FROM tour WHERE " + Fields.ID + "=?;";






    //Order
    String SQL_FIND_ORDER_BY_ID = "SELECT * FROM tour_order WHERE " + Fields.ID + "=?;";

    String SQL_FIND_ALL_ORDERS = "SELECT * FROM tour_order;";

    String SQL_INSERT_ORDER = "INSERT INTO tour_order ("
            + Fields.ORDER_DATE + ", " + Fields.ORDER_STATUS_ID + ", " +  Fields.ORDER_CLIENT_ID + ", " + Fields.ORDER_PRICE
            + ") VALUES (?, ?, ?, ?);";

    String SQL_UPDATE_ORDER = "UPDATE tour_order SET "
            + Fields.ORDER_DATE + "=?, " + Fields.ORDER_STATUS_ID + "=?, " + Fields.ORDER_CLIENT_ID + "=?, "
            + Fields.ORDER_PRICE + "=?" + " WHERE " + Fields.ID + "=?;";

    String SQL_DELETE_ORDER = "DELETE FROM tour_order WHERE " + Fields.ID + "=?;";





    //Hotel
    String SQL_FIND_HOTEL_BY_ID = "SELECT * FROM hotel WHERE " + Fields.ID + "=?";

    String SQL_FIND_ALL_HOTELS = "SELECT * FROM hotel";

    String SQL_INSERT_HOTEL = "INSERT INTO hotel ("
            + Fields.HOTEL_NAME + ", " + Fields.HOTEL_CITY + ", " +  Fields.HOTEL_ADDRESS
            + ", " + Fields.HOTEL_TYPE + ", " + Fields.HOTEL_ADMIN_PHONE
            + ") VALUES (?, ?, ?, ?, ?);";


    String SQL_UPDATE_HOTEL = "UPDATE hotel SET "
            + Fields.HOTEL_NAME + "=?, " + Fields.HOTEL_CITY + "=?, " + Fields.HOTEL_ADDRESS + "=?, "
            + Fields.HOTEL_TYPE + "=?, " + Fields.HOTEL_ADMIN_PHONE + "=?" + " WHERE " + Fields.ID + "=?;";

    String SQL_DELETE_HOTEL = "DELETE FROM hotel WHERE " + Fields.ID + "=?;";




    //Discount
    String SQL_FIND_DISCOUNT_BY_ID = "SELECT * FROM discount WHERE " + Fields.ID + "=?";

    String SQL_FIND_ALL_DISCOUNTS = "SELECT * FROM discount";

    String SQL_INSERT_DISCOUNT = "INSERT INTO discount ("
            + Fields.DISCOUNT_PERCENT + ", " + Fields.DISCOUNT_MAX_PERCENT
            +") VALUES (?, ?);";


    String SQL_UPDATE_DISCOUNT = "UPDATE discount SET "
            + Fields.DISCOUNT_PERCENT + "=?, " + Fields.DISCOUNT_MAX_PERCENT + "=? WHERE " + Fields.ID + "=?;";

    String SQL_DELETE_DISCOUNT = "DELETE FROM discount WHERE " + Fields.ID + "=?;";
}
