package com.example.touragency.model.dao.mapper.bean.ordertour;

import com.example.touragency.model.dao.beans.OrderTourBean;
import com.example.touragency.model.dao.mapper.EntityMapper;
import com.example.touragency.model.dao.mapper.bean.ordertour.enums.TourBeanMapper;
import com.example.touragency.model.dao.mapper.entity.OrderMapper;
import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.Tour;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderTourBeanMapper implements EntityMapper<OrderTourBean> {


    @Override
    public OrderTourBean extractFromResultSet(ResultSet rs) throws SQLException {
        Order order = new OrderMapper().extractFromResultSet(rs);
        Tour tour = new TourBeanMapper().extractFromResultSet(rs);
        return OrderTourBean.createOrderTourBean(order, tour);
    }
}
