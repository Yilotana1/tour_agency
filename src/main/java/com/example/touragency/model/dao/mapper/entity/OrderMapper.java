package com.example.touragency.model.dao.mapper.entity;

import com.example.touragency.model.Tools;
import com.example.touragency.constants.Fields;
import com.example.touragency.model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements EntityMapper<Order> {

    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {

        return Order.createOrder(rs.getInt(Fields.ID), Tools.getCalendarFromDate(
                rs.getDate(Fields.ORDER_DATE)),
                new OrderStatusMapper().extractFromResultSet(rs),
                new UserMapper().extractFromResultSet(rs),
                rs.getBigDecimal(Fields.ORDER_PRICE),
                rs.getInt(Fields.ORDER_TOUR_COUNT));
    }
}
