package com.example.touragency.model.dao.mapper.bean.orderclient.enums;

import com.example.touragency.constants.Fields;
import com.example.touragency.model.dao.mapper.EntityMapper;
import com.example.touragency.model.entity.UserStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserStatusBeanMapper implements EntityMapper<UserStatus> {

    @Override
    public UserStatus extractFromResultSet(ResultSet rs) throws SQLException {

        int id = rs.getInt(Fields.BEAN_USER_STATUS_ID);

        if (id == UserStatus.NON_BLOCKED.getId()){
            return UserStatus.NON_BLOCKED;
        }
        if (id == UserStatus.BLOCKED.getId()){
            return UserStatus.BLOCKED;
        }
        return null;
    }
}
