package com.example.touragency.model.entity.enums;

public enum TourStatus {
    BURNING, NON_BURNING;

    private final int id = this.ordinal() + 1;

    public int getId() {
        return id;
    }

    public static TourStatus getById(int id){

        switch (id){
            case 1: return BURNING;
            case 2: return NON_BURNING;
        }

        return BURNING;
    }
}
