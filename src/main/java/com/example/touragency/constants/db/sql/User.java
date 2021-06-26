package com.example.touragency.constants.db.sql;

import com.example.touragency.constants.db.Fields;
import com.example.touragency.constants.db.Tables;

public interface User {

    String SQL_FIND_USER_BY_ID = "SELECT * FROM " + Tables.USER + " WHERE " + Fields.USER_ID + "=?;";

    String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM " + Tables.USER + " WHERE " + Fields.USER_LOGIN + "=?;";

    String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM " + Tables.USER + " WHERE " + Fields.USER_EMAIL + "=?;";

    String SQL_FIND_USER_BY_PHONE = "SELECT * FROM " + Tables.USER + " WHERE " + Fields.USER_PHONE + "=?;";

    String SQL_FIND_ALL_USERS = "SELECT * FROM " + Tables.USER + ";";

    String SQL_FIND_USERS_BY_LIMIT = "SELECT * FROM " + Tables.USER + " LIMIT ?, ?";

    String SQL_FIND_USERS_BY_LIMIT_CLIENTS_FIRST = "SELECT * FROM " + Tables.USER + " ORDER BY " + Fields.USER_ROLE_ID + " LIMIT ?, ?";

    String SQL_FIND_USERS_BY_LIMIT_MANAGERS_FIRST = "SELECT * FROM " + Tables.USER + " ORDER BY " + Fields.USER_ROLE_ID + " DESC LIMIT ?, ?";

    String SQL_FIND_USERS_BY_LIMIT_NON_BLOCKED_FIRST = "SELECT * FROM " + Tables.USER + " ORDER BY " + Fields.USER_STATUS_ID + " LIMIT ?, ?";

    String SQL_FIND_USERS_BY_LIMIT_BLOCKED_FIRST = "SELECT * FROM " + Tables.USER + " ORDER BY " + Fields.USER_STATUS_ID + " DESC LIMIT ?, ?";


    String SQL_FIND_USERS_NUMBER_AS_COUNT = "SELECT COUNT(" + Fields.USER_ID + ") AS count FROM " + Tables.USER;

    String SQL_INSERT_USER = "INSERT INTO " + Tables.USER +"("
            + Fields.USER_LOGIN + ", " + Fields.USER_PASSWORD + ", " + Fields.USER_FIRSTNAME + ", "
            + Fields.USER_LASTNAME + ", " + Fields.USER_EMAIL + ", " + Fields.USER_PHONE + ", "
            + Fields.USER_ROLE_ID + ", " + Fields.USER_STATUS_ID
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    String SQL_UPDATE_USER = "UPDATE " + Tables.USER + " SET "
            + Fields.USER_LOGIN + "=?, " + Fields.USER_PASSWORD + "=?, " + Fields.USER_FIRSTNAME + "=?, "
            + Fields.USER_LASTNAME + "=?, " + Fields.USER_EMAIL + "=?," + Fields.USER_PHONE + "=?, "
            + Fields.USER_ROLE_ID + "=?, " + Fields.USER_STATUS_ID + "=? WHERE " + Fields.USER_ID + "=?;";

    String SQL_UPDATE_USER_BY_LOGIN = "UPDATE " + Tables.USER + " SET "
            + Fields.USER_LOGIN + "=?, " + Fields.USER_PASSWORD + "=?, " + Fields.USER_FIRSTNAME + "=?, "
            + Fields.USER_LASTNAME + "=?, " + Fields.USER_EMAIL + "=?," + Fields.USER_PHONE + "=?, "
            + Fields.USER_ROLE_ID + "=?, " + Fields.USER_STATUS_ID + "=? WHERE " + Fields.USER_LOGIN + "=?;";

    String SQL_DELETE_USER = "DELETE FROM " + Tables.USER + " WHERE " + Fields.ID + "=?;";
}
