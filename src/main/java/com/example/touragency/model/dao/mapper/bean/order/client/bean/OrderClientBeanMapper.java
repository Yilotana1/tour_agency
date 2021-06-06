package com.example.touragency.model.dao.mapper.bean.order.client.bean;

import com.example.touragency.model.dao.beans.OrderClientBean;
import com.example.touragency.model.dao.mapper.EntityMapper;
import com.example.touragency.model.dao.mapper.entity.OrderMapper;
import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderClientBeanMapper implements EntityMapper<OrderClientBean> {


    @Override
    public OrderClientBean extractFromResultSet(ResultSet rs) throws SQLException {
        Order order = new OrderMapper().extractFromResultSet(rs);
        User client = new BeanUserMapper().extractFromResultSet(rs);
        return OrderClientBean.createOrderClientBean(order, client);
    }
}
