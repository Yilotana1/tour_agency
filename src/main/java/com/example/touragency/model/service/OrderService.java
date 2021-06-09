package com.example.touragency.model.service;

import com.example.touragency.model.dao.beans.OrderClientBean;
import com.example.touragency.model.dao.beans.OrderTourBean;
import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.exceptions.ServiceException;

import java.util.List;

public interface OrderService {


    List<Order> getAllOrders() throws ServiceException;

    List<OrderClientBean> getAllOrderClientBeans() throws ServiceException;

    List<OrderTourBean> getAllOrderTourBeans() throws ServiceException;


    Order getOrderById(int id) throws ServiceException;

    OrderClientBean getOrderClientBeanById(int id) throws ServiceException;

    OrderTourBean getOrderTourBeanById(int id) throws ServiceException;



    List<OrderTourBean> getOrderTourBeansByClientId(int id) throws ServiceException;

    List<OrderTourBean> getOrderTourBeansByClientLogin(String login) throws ServiceException;


    void applyForOrder(Tour tour, User user) throws ServiceException;


    void confirmOrderPaid(Order order) throws ServiceException;


    void cancelOrder(Order order) throws ServiceException;


    void removeOrder(Order order) throws ServiceException;


}
