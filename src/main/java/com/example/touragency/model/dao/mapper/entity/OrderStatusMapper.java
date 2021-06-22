package com.example.touragency.model.dao.mapper.entity;

import com.example.touragency.constants.db.Fields;
import com.example.touragency.model.entity.enums.OrderStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderStatusMapper implements EntityMapper<OrderStatus> {


    @Override
    public OrderStatus extractFromResultSet(ResultSet rs) throws SQLException {

        int id = rs.getInt(Fields.ORDER_STATUS_ID);
        if (id == OrderStatus.OPENED.getId()){
            return OrderStatus.OPENED;
        }
        if (id == OrderStatus.PAID.getId()){
            return OrderStatus.PAID;
        }

        if (id == OrderStatus.CANCELED.getId()){
            return OrderStatus.CANCELED;
        }
        return null;
    }
}
