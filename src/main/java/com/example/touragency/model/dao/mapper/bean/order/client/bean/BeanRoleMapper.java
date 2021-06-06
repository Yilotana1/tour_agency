package com.example.touragency.model.dao.mapper.bean.order.client.bean;

import com.example.touragency.constants.Fields;
import com.example.touragency.model.dao.mapper.EntityMapper;
import com.example.touragency.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BeanRoleMapper implements EntityMapper<Role> {


    @Override
    public Role extractFromResultSet(ResultSet rs) throws SQLException {


        int id = rs.getInt(Fields.BEAN_USER_ROLE_ID);
        if (id == Role.CLIENT.getId()){
            return Role.CLIENT;
        }
        if (id == Role.ADMIN.getId()){
            return Role.ADMIN;
        }
        if (id == Role.MANAGER.getId()){
            return Role.MANAGER;
        }
        return null;
    }
}
