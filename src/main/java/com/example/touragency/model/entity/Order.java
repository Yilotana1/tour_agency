package com.example.touragency.model.entity;

import java.math.BigDecimal;
import java.util.Calendar;

public class Order extends Entity {

    private Calendar date;
    private OrderStatus status;
    private int client_id;
    private BigDecimal price;

    private Order(){}

    public static Order createOrder(int id, Calendar date, OrderStatus status, int client_id, BigDecimal price) {
        Order order = new Order();
        order.setId(id);
        order.setDate(date);
        order.setStatus(status);
        order.setClientId(client_id);
        order.setPrice(price);
        return order;
    }

    @Override
    public String toString() {
        return "Order{" +
                ", date=" + date.getTime() +
                ", status=" + status +
                ", client_id=" + client_id +
                ", price=" + price +
                '}';
    }


    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setClientId(int client_id) {
        this.client_id = client_id;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Calendar getDate() {
        return date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public int getClientId() {
        return client_id;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
