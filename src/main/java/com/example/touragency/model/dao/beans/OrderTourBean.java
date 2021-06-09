package com.example.touragency.model.dao.beans;

import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.Tour;

public class OrderTourBean {
    private Order order;
    private Tour tour;

    private OrderTourBean(){}

    public static OrderTourBean createOrderTourBean(Order order, Tour tour){
        OrderTourBean orderTourBean = new OrderTourBean();
        orderTourBean.setOrder(order);
        orderTourBean.setTour(tour);
        return orderTourBean;
    }

    @Override
    public String toString() {
        return "OrderClientBean{" +
                "order=" + order +
                ", tour=" + tour +
                '}';
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Order getOrder() {
        return order;
    }

    public Tour getTour() {
        return tour;
    }
}
