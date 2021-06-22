package com.example.touragency.model.dao.mapper.entity;

import com.example.touragency.constants.db.Fields;
import com.example.touragency.model.entity.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RoleMapper implements EntityMapper<Role> {


    @Override
    public Role extractFromResultSet(ResultSet rs) throws SQLException {


        int id = rs.getInt(Fields.USER_ROLE_ID);
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
