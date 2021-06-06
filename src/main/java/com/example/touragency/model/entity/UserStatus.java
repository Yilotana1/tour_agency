package com.example.touragency.model.entity;

public enum UserStatus {
    BLOCKED, NON_BLOCKED;

    private final int id = this.ordinal() + 1;

    public int getId() {
        return id;
    }
}
