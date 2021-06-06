package com.example.touragency.model.dao.mapper;

import com.example.touragency.constants.Fields;
import com.example.touragency.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements EntityMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return User.createUser(rs.getInt(Fields.ID), rs.getString(Fields.USER_FIRSTNAME),
                rs.getString(Fields.USER_LASTNAME), rs.getString(Fields.USER_PHONE),
                rs.getString(Fields.USER_EMAIL), new UserStatusMapper().extractFromResultSet(rs),
                rs.getString(Fields.USER_LOGIN), rs.getString(Fields.USER_PASSWORD),
                new RoleMapper().extractFromResultSet(rs));
    }

}
