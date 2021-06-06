package com.example.touragency.model.entity;

public enum Role {
    CLIENT, ADMIN, MANAGER;

    private final int id = this.ordinal() + 1;

    public int getId() {
        return id;
    }
}
