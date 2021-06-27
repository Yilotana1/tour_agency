package com.example.touragency.model.service;

import com.example.touragency.model.entity.Discount;

public interface DiscountService extends Service<Discount>{

    void update(int percentStep, int maxPercent);

    Discount getDiscount();

}
