package com.example.touragency.model.dao.mapper;

import com.example.touragency.Tools;
import com.example.touragency.constants.db.Fields;
import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.enums.OrderStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements EntityMapper<Order> {

    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {

        return Order.createOrder(rs.getInt(Fields.ID), Tools.getCalendarFromDate(
                rs.getDate(Fields.ORDER_DATE)),
                OrderStatus.getById(rs.getInt(Fields.ORDER_STATUS_ID)),
                new UserMapper().extractFromResultSet(rs),
                rs.getBigDecimal(Fields.ORDER_PRICE), rs.getString(Fields.TOUR_NAME), rs.getInt(Fields.TOUR_ID));
    }

}
