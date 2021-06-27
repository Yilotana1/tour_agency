package com.example.touragency.model.dao;

import com.example.touragency.model.entity.Tour;
import java.util.List;
import java.util.Optional;

public interface TourDao extends GenericDao<Tour>{



    Optional<Tour> findById(int id);

    Optional<Tour> findByName(String name);

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

}
