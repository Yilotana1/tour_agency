package com.example.touragency.model.dao;

import com.example.touragency.model.entity.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order>{

    List<Order> findOrdersByLogin(String login);

    List<Order> findOrdersByLimitOpenedFirst(int start, int count);

    List<Order> findOrdersByLimitPaidFirst(int start, int count);


}
