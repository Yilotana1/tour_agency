package com.example.touragency.model.entity;

import com.example.touragency.model.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Order extends Entity {

    private User client;
    private String tourName;
    private int tourId;
    private Calendar date;
    private OrderStatus status;
    private BigDecimal price;

    private Order(){}

    public static Order createOrder(int id, Calendar date, OrderStatus status,
                                    User client, BigDecimal price, String tourName, int tourId) {
        Order order = new Order();
        order.setId(id);
        order.setDate(date);
        order.setStatus(status);
        order.setClient(client);
        order.setPrice(price);
        order.setTourName(tourName);
        order.setTourId(tourId);
        return order;
    }

    public static Order createOrder(Calendar date, OrderStatus status,
                                    User client, BigDecimal price, String tourName, int tourId) {
        Order order = new Order();
        order.setDate(date);
        order.setStatus(status);
        order.setClient(client);
        order.setPrice(price);
        order.setTourName(tourName);
        order.setTourId(tourId);
        return order;
    }


    @Override
    public String toString() {
        return "Order{" +
                "tourName=" + tourName +
                ", date=" + new SimpleDateFormat("yyyy-MM-dd HH").format(date.getTime()) +
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

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    private void setDate(Calendar date) {
        this.date = date;
    }

    public String getTourName() {
        return tourName;
    }

    public int getTourId() {
        return tourId;
    }

    public Calendar getDate() {

        return date;
    }
    public String getDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH").format(date.getTime());
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

}
