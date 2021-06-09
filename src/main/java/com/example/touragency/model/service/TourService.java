package com.example.touragency.model.service;

import com.example.touragency.model.dao.beans.TourHotelBean;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.TourCategory;
import com.example.touragency.model.entity.TourStatus;
import com.example.touragency.model.exceptions.ServiceException;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public interface TourService {



    void addTour(String name, String country, BigDecimal price, int maxPlaces, int minPlaces,
                 int takenPlaces, Calendar startDate, Calendar endDate, TourCategory category,
                 TourStatus status, int hotel_id, String city) throws ServiceException;

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

    void changeTourHotelId(int id, int hotel_id) throws ServiceException;

    void changeTourCity(int id, String name) throws ServiceException;

    void changeTourStatus(int id, TourStatus status) throws ServiceException;




    List<TourHotelBean> getAllTours() throws ServiceException;

    List<TourHotelBean> getToursByCategory(TourCategory category) throws ServiceException;


    TourHotelBean getTourById(int id) throws ServiceException;

    TourHotelBean getTourByName(String name) throws ServiceException;



    List<TourHotelBean> getToursByPriceMoreThan(BigDecimal price) throws ServiceException;

    List<TourHotelBean> getToursByPriceLessThan(BigDecimal price) throws ServiceException;


    List<TourHotelBean> getToursByPeopleMoreThan(int peopleNumber) throws ServiceException;

    List<TourHotelBean> getToursByPeopleLessThan(int peopleNumber) throws ServiceException;


    List<TourHotelBean> getToursByHotelStarsMoreThan(int type) throws ServiceException;

    List<TourHotelBean> getToursByHotelStarsLessThan(int type) throws ServiceException;







}
