package com.example.touragency.model.entity.enums;

public enum TourCategory {
    EXCURSION, SHOPPING, REST;

    private final int id = this.ordinal() + 1;

    public int getId() {
        return id;
    }

    public static TourCategory getById(int id){

        switch (id){
            case 1: return EXCURSION;
            case 2: return SHOPPING;
            case 3: return REST;
        }

        return EXCURSION;
    }

}
