package com.example.touragency.model.service;

import com.example.touragency.model.entity.Discount;

import java.util.Optional;

public interface DiscountService extends Service<Discount>{

    void update(int percentStep, int maxPercent);

    Optional<Discount> getDiscount();

}
