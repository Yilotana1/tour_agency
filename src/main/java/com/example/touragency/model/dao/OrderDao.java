package com.example.touragency.model.dao;

import com.example.touragency.model.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends GenericDao<Order>{

    List<Order> findOrdersByLogin(String login) throws SQLException;

    List<Order> findOrdersByLimitOpenedFirst(int start, int count) throws SQLException;

    List<Order> findOrdersByLimitPaidFirst(int start, int count) throws SQLException;


}
