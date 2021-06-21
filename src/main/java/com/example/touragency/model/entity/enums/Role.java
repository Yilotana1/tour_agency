package com.example.touragency.model.entity.enums;

public enum Role {
    CLIENT, MANAGER, ADMIN, UNKNOWN;

    private final int id = this.ordinal() + 1;

    public int getId() {
        return id;
    }

    public static Role getById(int id){

        switch (id){
            case 1: return CLIENT;
            case 2: return MANAGER;
            case 3: return ADMIN;
            case 4: return UNKNOWN;
        }

        return UNKNOWN;
    }
}
