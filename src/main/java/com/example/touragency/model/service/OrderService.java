package com.example.touragency.model.service;

import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.exceptions.ServiceException;

import java.util.List;

public interface OrderService {


    List<Order> getAllOrders() throws ServiceException;

    List<Order> getOpenedOrders() throws ServiceException;

    List<Order> getPaidOrders() throws ServiceException;

    List<Order> getCanceledOrders() throws ServiceException;



    Order getOrderById(int id) throws ServiceException;


    void applyForOrder(List<String> tourNames, String clientLogin) throws ServiceException;


    void confirmOrderPaid(int id) throws ServiceException;


    void cancelOrder(int id) throws ServiceException;


    void removeOrder(int id) throws ServiceException;


}
