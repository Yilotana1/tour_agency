package com.example.touragency.model.service;

import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.enums.TourCategory;
import com.example.touragency.model.entity.enums.TourStatus;
import com.example.touragency.model.entity.enums.TourStatus;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public interface TourService extends Service<Tour>{


    List<Tour> getSortedByStatus();

    Tour getByName(String name);

    List<Tour> getPageCountry(int pageId, int pageSize, String country);

    List<Tour> getPageBurningFirst(int pageId, int pageSize);

    List<Tour> getPageNonBurningFirst(int pageId, int pageSize);

    List<Tour> getPageHighHotelStarsFirst(int pageId, int pageSize);

    List<Tour> getPageLowHotelStarsFirst(int pageId, int pageSize);

    List<Tour> getPageHighPriceFirst(int pageId, int pageSize);

    List<Tour> getPageLowPriceFirst(int pageId, int pageSize);

    List<Tour> getPageExcursion(int pageId, int pageSize);

    List<Tour> getPageRest(int pageId, int pageSize);

    List<Tour> getPageShopping(int pageId, int pageSize);


    List<Tour> getWithPriceLessThan(BigDecimal price);

    List<Tour> getWithPriceMoreThan(BigDecimal price);

    List<Tour> getWithPeopleMoreThan(int maxPlaces);

    List<Tour> getWithPeopleLessThan(int maxPlaces);

    List<Tour> getWithHotelStarsMoreThan(int stars);

    List<Tour> getWithHotelStarsLessThan(int stars);

    List<Tour> getByCategory(TourCategory category);

    List<Tour> getByCountry(String country);

    void changeCountry(int id, String country);

    void changeName(int id, String name);

    void changePrice(int id, BigDecimal price);

    void changeMaxPlaces(int id, int max_places);

    void changeMinPlaces(int id, int min_places);

    void changeStartDate(int id, Calendar startDate);

    void changeEndDate(int id, Calendar endDate);

    void changeCategory(int id, TourCategory category);

    void changeHotel(int id, String hotelName);

    void changeCity(int id, String name);

    void changeStatus(int id, TourStatus status);

}
