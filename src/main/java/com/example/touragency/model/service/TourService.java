package com.example.touragency.model.service;

import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.enums.TourCategory;
import com.example.touragency.model.entity.enums.TourStatus;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public interface TourService {


    List<Tour> getToursSortedByStatus();

    Tour getTourById(int id);

    Tour getTourByName(String name);

    List<Tour> getToursWithPriceLessThan(BigDecimal price);

    List<Tour> getToursWithPriceMoreThan(BigDecimal price);

    List<Tour> getToursWithPeopleMoreThan(int maxPlaces);

    List<Tour> getToursWithPeopleLessThan(int maxPlaces);

    List<Tour> getToursWithHotelStarsMoreThan(int stars);

    List<Tour> getToursWithHotelStarsLessThan(int stars);

    List<Tour> getToursByCategory(TourCategory category);

    List<Tour> getToursByCountry(String country);


    void addTour(Tour tour);

    void removeTour(int id);

    void changeTourCountry(int id, String country);

    void changeTourName(int id, String name);

    void changeTourPrice(int id, BigDecimal price);

    void changeTourMaxPlaces(int id, int max_places);

    void changeTourMinPlaces(int id, int min_places);

    void changeTourStart(int id, Calendar startDate);

    void changeTourEnd(int id, Calendar endDate);

    void changeTourCategory(int id, TourCategory category);

    void changeTourHotel(int id, String hotelName);

    void changeTourCity(int id, String name);

    void changeTourStatus(int id, TourStatus status);

}
