package com.example.touragency.model.service;

import com.example.touragency.model.entity.Order;

import java.util.List;

public interface OrderService extends Service<Order>{


    List<Order> getPageOpenedFirst(int start, int count);

    List<Order> getPagePaidFirst(int start, int count);

    List<Order> getOpened();

    List<Order> getPaid();

    List<Order> getCanceled();

    List<Order> getByClientId(int clientId);

    List<Order> getByLogin(String login);


    void applyForOrder(int tourId, int clientId);

    void confirmPaid(int id);

    void cancel(int id);


//    void removeOrder(int id);


}
