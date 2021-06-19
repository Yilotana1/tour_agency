package com.example.touragency.model.service;

import com.example.touragency.model.entity.Order;

import java.util.List;

public interface OrderService {


    List<Order> getAllOrders();

    List<Order> getOpenedOrders();

    List<Order> getPaidOrders();

    List<Order> getCanceledOrders();



    Order getOrderById(int id);


    void applyForOrder(List<String> tourNames, String clientLogin);


    void confirmOrderPaid(int id);


    void cancelOrder(int id);


    void removeOrder(int id);


}
