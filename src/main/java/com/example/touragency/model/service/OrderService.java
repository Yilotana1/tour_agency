package com.example.touragency.model.service;

import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.exceptions.ServiceException;

import java.util.List;

public interface OrderService {


    List<Order> getAllOrders() throws ServiceException;

    Order getOrderById(int id) throws ServiceException;


    void applyForOrder(String tourName, String clientLogin) throws ServiceException;


    void confirmOrderPaid(Order order) throws ServiceException;


    void cancelOrder(Order order) throws ServiceException;


    void removeOrder(Order order) throws ServiceException;


}
