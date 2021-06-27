package com.example.touragency.model.dao.mapper;

import com.example.touragency.constants.db.Fields;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.entity.enums.UserStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements EntityMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return User.createUser(rs.getInt(Fields.ID), rs.getString(Fields.USER_FIRSTNAME),
                rs.getString(Fields.USER_LASTNAME), rs.getString(Fields.USER_PHONE),
                rs.getString(Fields.USER_EMAIL), UserStatus.getById(rs.getInt(Fields.USER_STATUS_ID)),
                rs.getString(Fields.USER_LOGIN), rs.getString(Fields.USER_PASSWORD),
                Role.getById(rs.getInt(Fields.USER_ROLE_ID)));
    }

}
