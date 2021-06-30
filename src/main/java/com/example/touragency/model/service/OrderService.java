package com.example.touragency.model.service;

import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.Order;

import java.util.List;

public interface OrderService extends Service<Order>{


    void setOpened(int id) throws ServiceException;

    void cancel(int id) throws ServiceException;

    void confirmPaid(int id) throws ServiceException;

    long getOpenedCount() throws ServiceException;

    List<Order> getPageOpenedFirst(int start, int count) throws ServiceException;

    List<Order> getPagePaidFirst(int start, int count) throws ServiceException;


    List<Order> getByLogin(String login) throws ServiceException;

    void applyForOrder(int tourId, String login) throws ServiceException;



}
