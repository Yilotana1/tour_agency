package com.example.touragency.model.entity.enums;

public enum Role {
    CLIENT, ADMIN, MANAGER, UNKNOWN;

    private final int id = this.ordinal() + 1;

    public int getId() {
        return id;
    }
}
