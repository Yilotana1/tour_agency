package com.example.touragency.model.service;

import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.dao.TourDao;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.enums.TourCategory;
import com.example.touragency.model.entity.enums.TourStatus;
import com.example.touragency.model.entity.enums.TourStatus;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

public interface TourService extends Service<Tour>{
    void create(String name, String country, BigDecimal price,
                int maxTickets, Calendar startDate, Calendar endDate, TourCategory category, TourStatus status,
                String hotelName, String city) throws ServiceException;

    void update(int id, String name, String country, BigDecimal price, int maxTickets,
                           int takenTickets, Calendar startDate, Calendar endDate, TourCategory category,
                           TourStatus status, String hotelName, String city) throws ServiceException;

    void deleteByName(String name) throws ServiceException;

    List<Tour> getPageName(int pageId, int pageSize, String name);

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

    void changeStatus(int id, TourStatus status);

}
