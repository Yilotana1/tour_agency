package com.example.touragency.model.entity.enums;


public enum UserStatus {
    NON_BLOCKED, BLOCKED;

    private final int id = this.ordinal() + 1;

    public int getId() {
        return id;
    }


    public static UserStatus getById(int id){

        switch (id){
            case 1: return NON_BLOCKED;
            case 2: return BLOCKED;
        }

        return NON_BLOCKED;
    }
}
