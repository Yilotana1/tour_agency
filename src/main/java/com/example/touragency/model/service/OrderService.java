package com.example.touragency.model.service;

import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.Order;

import java.util.List;

public interface OrderService extends Service<Order>{


    void setOpened(int id);

    void cancel(int id);

    void confirmPaid(int id);

    List<Order> getPageOpenedFirst(int start, int count);

    List<Order> getPagePaidFirst(int start, int count);


    List<Order> getByLogin(String login);

    void applyForOrder(int tourId, String login) throws ServiceException;



}
