package com.example.touragency.model.dao.mapper.bean.orderclient;

import com.example.touragency.constants.Fields;
import com.example.touragency.model.dao.mapper.EntityMapper;
import com.example.touragency.model.dao.mapper.bean.orderclient.enums.UserStatusBeanMapper;
import com.example.touragency.model.dao.mapper.enums.RoleMapper;
import com.example.touragency.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBeanMapper implements EntityMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return User.createUser(rs.getInt(Fields.BEAN_USER_ID), rs.getString(Fields.BEAN_USER_FIRSTNAME),
                rs.getString(Fields.BEAN_USER_LASTNAME), rs.getString(Fields.BEAN_USER_PHONE),
                rs.getString(Fields.BEAN_USER_EMAIL), new UserStatusBeanMapper().extractFromResultSet(rs),
                rs.getString(Fields.BEAN_USER_LOGIN), rs.getString(Fields.BEAN_USER_PASSWORD),
                new RoleMapper().extractFromResultSet(rs));
    }
}
