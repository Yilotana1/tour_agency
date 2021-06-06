package com.example.touragency.model.service;

import com.example.touragency.model.dao.beans.TourHotelBean;
import com.example.touragency.model.entity.TourCategory;
import com.example.touragency.model.exceptions.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface TourService {


    List<TourHotelBean> getToursByCategory(TourCategory category) throws ServiceException;

    List<TourHotelBean> getToursByPrice(BigDecimal price) throws ServiceException;

    List<TourHotelBean> getToursByPeople(int peopleNumber) throws ServiceException;

    List<TourHotelBean> getToursByHotelType(int type) throws ServiceException;





}
