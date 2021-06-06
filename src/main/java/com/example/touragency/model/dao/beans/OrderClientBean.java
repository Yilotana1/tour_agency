package com.example.touragency.model.dao.beans;

import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.User;

public class OrderClientBean {
    private Order order;
    private User client;

    private OrderClientBean(){}

    public static OrderClientBean createOrderClientBean(Order order, User client){
        OrderClientBean orderClientBean = new OrderClientBean();
        orderClientBean.setOrder(order);
        orderClientBean.setClient(client);
        return orderClientBean;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Order getOrder() {
        return order;
    }

    public User getClient() {
        return client;
    }
}
