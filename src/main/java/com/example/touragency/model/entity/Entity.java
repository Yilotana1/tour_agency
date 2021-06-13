package com.example.touragency.model.entity;

public class Entity {

    private int id;

    public int getId(){
        return this.id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return id == entity.id;
    }
}
