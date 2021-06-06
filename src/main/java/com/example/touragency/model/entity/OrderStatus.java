package com.example.touragency.model.entity;

public enum OrderStatus {
    OPENED, PAID, CANCELED;

    private final int id = this.ordinal() + 1;

    public int getId() {
        return id;
    }
}
