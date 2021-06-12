package com.example.touragency.model.entity;

import com.example.touragency.model.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Order extends Entity {

    private User client;
    private int tourNumber;
    private Calendar date;
    private OrderStatus status;
    private BigDecimal price;

    private Order(){}

    public static Order createOrder(int id, Calendar date, OrderStatus status,
                                    User client, BigDecimal price, int tourNumber) {
        Order order = new Order();
        order.setId(id);
        order.setDate(date);
        order.setStatus(status);
        order.setClient(client);
        order.setPrice(price);
        order.setTourNumber(tourNumber);
        return order;
    }

    @Override
    public String toString() {
        return "Order{" +
                "tourNumber=" + tourNumber +
                ", date=" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date.getTime()) +
                ", status=" + status +
                ", clientLogin=" +  client.getLogin() +
                ", price=" + price +
                '}';
    }


    public void setClient(User client) {
        this.client = client;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setTourNumber(int tourNumber) {
        this.tourNumber = tourNumber;
    }

    public int getTourNumber() {
        return tourNumber;
    }

    public Calendar getDate() {
        return date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public User getClient() {
        return client;
    }

    private void setDate(Calendar date) {
        this.date = date;
    }

}
