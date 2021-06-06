package com.example.touragency.model.entity;

public class Discount extends Entity {
    private int percent;
    private int maxPercent;


    private Discount() {
    }

    public static Discount createDiscount(int id, int percent, int maxPercent) {
        Discount discount = new Discount();
        discount.setId(id);
        discount.setPercent(percent);
        discount.setMaxPercent(maxPercent);
        return discount;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "percent=" + percent +
                ", maxPercent=" + maxPercent +
                '}';
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public void setMaxPercent(int maxPercent) {
        this.maxPercent = maxPercent;
    }

    public int getPercent() {
        return percent;
    }

    public int getMaxPercent() {
        return maxPercent;
    }
}
