package com.example.touragency.model.service;

import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.Order;

import java.util.List;

public interface OrderService extends Service<Order>{


    void setOpened(int id) throws ServiceException;

    void cancel(int id) throws ServiceException;

    void confirmPaid(int id) throws ServiceException;

    List<Order> getPageOpenedFirst(int start, int count);

    List<Order> getPagePaidFirst(int start, int count);


    List<Order> getByLogin(String login);

    void applyForOrder(int tourId, String login) throws ServiceException;



}
