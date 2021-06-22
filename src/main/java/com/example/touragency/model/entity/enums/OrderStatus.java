package com.example.touragency.model.entity.enums;

public enum OrderStatus {
    OPENED, CANCELED, PAID;

    private final int id = this.ordinal() + 1;

    public int getId() {
        return id;
    }

    public static OrderStatus getById(int id){

        switch (id){
            case 1: return OPENED;
            case 2: return CANCELED;
            case 3: return PAID;
        }

        return OPENED;
    }
}
