package com.example.touragency.model.dao;

import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.enums.TourCategory;

import java.math.BigDecimal;
import java.util.List;

public interface TourDao extends GenericDao<Tour>{


    void addTourToOrder(int tourId, int orderId);

    Tour findById(int id);

    Tour findByName(String name);



    List<Tour> findByOrderId(int id);

    List<Tour> findByCategory(TourCategory category);

    List<Tour> findByPrice(BigDecimal price);

    List<Tour> findByPeople(int peopleNumber);

    List<Tour> findByHotelId(int hotelId);



}
