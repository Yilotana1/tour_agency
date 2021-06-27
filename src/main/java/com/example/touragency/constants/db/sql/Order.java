package com.example.touragency.constants.db.sql;

import com.example.touragency.constants.db.Fields;
import com.example.touragency.constants.db.Tables;

public interface Order {



    String SQL_FIND_ORDER_BY_ID = "SELECT " + Tables.ORDER + ".*," + Fields.TOUR_ID + ","
            + Fields.TOUR_NAME + ", " + Tables.USER + ".* " + " FROM " + Tables.ORDER + " JOIN " + Tables.USER + " ON " + Fields.ORDER_CLIENT_ID + " = " + Fields.USER_ID +
            " JOIN " + Tables.TOUR + " ON " + Fields.ORDER_TOUR_ID + " = " + Fields.TOUR_ID + " WHERE " + Fields.ORDER_ID + " = ?";

    String SQL_FIND_ORDER_BY_LOGIN = "SELECT " + Tables.ORDER + ".*," + Fields.TOUR_ID + ","
            + Fields.TOUR_NAME + ", " + Tables.USER + ".* " + " FROM " + Tables.ORDER + " JOIN " + Tables.USER + " ON " + Fields.ORDER_CLIENT_ID + " = " + Fields.USER_ID +
            " JOIN " + Tables.TOUR + " ON " + Fields.ORDER_TOUR_ID + " = " + Fields.TOUR_ID + " WHERE " + Fields.USER_LOGIN + " = ?";


    String SQL_FIND_ORDERS_BY_LIMIT = "SELECT " + Tables.ORDER + ".*," + Fields.TOUR_ID + ","
            + Fields.TOUR_NAME + ", " + Tables.USER + ".* " + " FROM " + Tables.ORDER
            + " JOIN " + Tables.USER + " ON " + Fields.ORDER_CLIENT_ID + " = " + Fields.USER_ID +
            " JOIN " + Tables.TOUR + " ON " + Fields.ORDER_TOUR_ID + " = " + Fields.TOUR_ID + " LIMIT ?, ?";

    String SQL_FIND_ORDERS_BY_LIMIT_OPENED_FIRST = "SELECT " + Tables.ORDER + ".*," + Fields.TOUR_ID + ","
            + Fields.TOUR_NAME + ", " + Tables.USER + ".* " + " FROM " + Tables.ORDER
            + " JOIN " + Tables.USER + " ON " + Fields.ORDER_CLIENT_ID + " = " + Fields.USER_ID +
            " JOIN " + Tables.TOUR + " ON " + Fields.ORDER_TOUR_ID + " = " + Fields.TOUR_ID + " ORDER BY " + Fields.ORDER_STATUS_ID + " LIMIT ?, ?";

    String SQL_FIND_ORDERS_BY_LIMIT_PAID_FIRST = "SELECT " + Tables.ORDER + ".*," + Fields.TOUR_ID + ","
            + Fields.TOUR_NAME + ", " + Tables.USER + ".* " + " FROM " + Tables.ORDER
            + " JOIN " + Tables.USER + " ON " + Fields.ORDER_CLIENT_ID + " = " + Fields.USER_ID +
            " JOIN " + Tables.TOUR + " ON " + Fields.ORDER_TOUR_ID + " = " + Fields.TOUR_ID + " ORDER BY " + Fields.ORDER_STATUS_ID + " DESC LIMIT ?, ?";

    String SQL_FIND_ALL_ORDERS = "SELECT " + Tables.ORDER + ".*," + Fields.TOUR_ID + ","
            + Fields.TOUR_NAME + ", " + Tables.USER + ".* " + " FROM " + Tables.ORDER
            + " JOIN " + Tables.USER + " ON " + Fields.ORDER_CLIENT_ID + " = " + Fields.USER_ID +
            " JOIN " + Tables.TOUR + " ON " + Fields.ORDER_TOUR_ID + " = " + Fields.TOUR_ID;

    String SQL_FIND_ORDERS_NUMBER_AS_COUNT = "SELECT COUNT(" + Fields.ORDER_ID + ") AS count FROM " + Tables.ORDER;

    String SQL_INSERT_ORDER = "INSERT INTO " + Tables.ORDER + "("
            + Fields.ORDER_DATE + ", " + Fields.ORDER_STATUS_ID + ", " + Fields.ORDER_CLIENT_ID + ", "
            + Fields.ORDER_PRICE + ", " + Fields.ORDER_TOUR_ID
            + ") VALUES (?, ?, ?, ?, ?);";

    String SQL_UPDATE_ORDER = "UPDATE " + Tables.ORDER +  " SET "
            + Fields.ORDER_DATE + "=?, " + Fields.ORDER_STATUS_ID + "=?, "
            + Fields.ORDER_PRICE + "=?," + Fields.ORDER_TOUR_ID + "=?" + " WHERE " + Fields.ORDER_ID + "=?";

    String SQL_DELETE_ORDER = "DELETE FROM " + Tables.ORDER + " WHERE " + Fields.ORDER_ID + "=?;";


}
