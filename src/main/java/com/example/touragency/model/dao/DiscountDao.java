package com.example.touragency.model.dao;

import com.example.touragency.model.entity.Discount;

import java.util.List;

public interface DiscountDao extends GenericDao<Discount>{

    List<Discount> findByLimit(int start, int count) throws UnsupportedOperationException;

}
