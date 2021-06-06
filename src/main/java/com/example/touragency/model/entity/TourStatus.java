package com.example.touragency.model.entity;

public enum TourStatus {
    BURNING, NON_BURNING;

    private final int id = this.ordinal() + 1;

    public int getId() {
        return id;
    }
}
