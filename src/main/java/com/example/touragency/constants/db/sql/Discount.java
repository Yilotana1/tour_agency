package com.example.touragency.constants.db.sql;

import com.example.touragency.constants.db.Fields;
import com.example.touragency.constants.db.Tables;

public interface Discount {

    String SQL_FIND_DISCOUNT_BY_ID = "SELECT * FROM " + Tables.DISCOUNT + " WHERE " + Fields.DISCOUNT_ID + "=?";

    String SQL_FIND_ALL_DISCOUNTS = "SELECT * FROM " + Tables.DISCOUNT;

    String SQL_INSERT_DISCOUNT = "INSERT INTO " + Tables.DISCOUNT + "("
            + Fields.DISCOUNT_PERCENT + ", " + Fields.DISCOUNT_MAX_PERCENT
            +") VALUES (?, ?);";


    String SQL_UPDATE_DISCOUNT = "UPDATE " + Tables.DISCOUNT + " SET "
            + Fields.DISCOUNT_PERCENT + "=?, " + Fields.DISCOUNT_MAX_PERCENT + "=? WHERE " + Fields.DISCOUNT_ID + "=?;";

    String SQL_DELETE_DISCOUNT = "DELETE FROM " + Tables.DISCOUNT + " WHERE " + Fields.DISCOUNT_ID + "=?;";
}
