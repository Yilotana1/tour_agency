package com.example.touragency.model.service;

import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.enums.TourCategory;
import com.example.touragency.model.entity.enums.TourStatus;
import com.example.touragency.model.exceptions.ServiceException;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public interface TourService {


    List<Tour> getToursSortedByStatus() throws ServiceException;

    Tour getTourById(int id) throws ServiceException;

    Tour getTourByName(String name) throws ServiceException;

    List<Tour> getToursWithPriceLessThan(BigDecimal price) throws ServiceException;

    List<Tour> getToursWithPriceMoreThan(BigDecimal price) throws ServiceException;

    List<Tour> getToursWithPeopleMoreThan(int maxPlaces) throws ServiceException;

    List<Tour> getToursWithPeopleLessThan(int maxPlaces) throws ServiceException;

    List<Tour> getToursWithHotelStarsMoreThan(int stars) throws ServiceException;

    List<Tour> getToursWithHotelStarsLessThan(int stars) throws ServiceException;

    List<Tour> getToursByCategory(TourCategory category) throws ServiceException;


    void addTour(Tour tour) throws ServiceException;

    void removeTour(int id) throws ServiceException;

    void changeTourCountry(int id, String country) throws ServiceException;

    void changeTourName(int id, String name) throws ServiceException;

    void changeTourPrice(int id, BigDecimal price) throws ServiceException;

    void changeTourMaxPlaces(int id, int max_places) throws ServiceException;

    void changeTourMinPlaces(int id, int min_places) throws ServiceException;

    void changeTourStart(int id, Calendar startDate) throws ServiceException;

    void changeTourEnd(int id, Calendar endDate) throws ServiceException;

    void changeTourCategory(int id, TourCategory category) throws ServiceException;

    void changeTourHotel(int id, String hotelName) throws ServiceException;

    void changeTourCity(int id, String name) throws ServiceException;

    void changeTourStatus(int id, TourStatus status) throws ServiceException;

}
