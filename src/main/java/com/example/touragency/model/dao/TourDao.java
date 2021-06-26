package com.example.touragency.model.dao;

import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.enums.TourCategory;

import java.math.BigDecimal;
import java.util.List;

public interface TourDao extends GenericDao<Tour>{



    Tour findById(int id);

    Tour findByName(String name);

    List<Tour> findByCategory(TourCategory category);


    List<Tour> findByLimitName(int start, int count, String name);

    List<Tour> findByLimitCountry(int start, int count, String country);

    List<Tour> findByLimit(int start, int count);

    List<Tour> findByLimitBurningFirst(int start, int count);

    List<Tour> findByLimitNonBurningFirst(int start, int count);

    List<Tour> findByLimitHighHotelStarsFirst(int start, int count);

    List<Tour> findByLimitLowHotelStarsFirst(int start, int count);

    List<Tour> findByLimitHighPriceFirst(int start, int count);

    List<Tour> findByLimitLowPriceFirst(int start, int count);

    List<Tour> findByLimitExcursion(int start, int count);

    List<Tour> findByLimitRest(int start, int count);

    List<Tour> findByLimitShopping(int start, int count);

    List<Tour> findByPrice(BigDecimal price);

    List<Tour> findByPeople(int peopleNumber);

    List<Tour> findByHotelId(int hotelId);



}
