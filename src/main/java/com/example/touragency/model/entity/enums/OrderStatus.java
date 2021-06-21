package com.example.touragency.model.entity.enums;

public enum OrderStatus {
    OPENED, PAID, CANCELED;

    private final int id = this.ordinal() + 1;

    public int getId() {
        return id;
    }

    public static OrderStatus getById(int id){

        switch (id){
            case 1: return OPENED;
            case 2: return PAID;
            case 3: return CANCELED;
        }

        return OPENED;
    }
}
