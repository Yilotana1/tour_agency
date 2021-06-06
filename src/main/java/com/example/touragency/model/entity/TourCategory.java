package com.example.touragency.model.entity;

public enum TourCategory {
    EXCURSION, SHOPPING, REST;

    private final int id = this.ordinal() + 1;

    public int getId() {
        return id;
    }
}
