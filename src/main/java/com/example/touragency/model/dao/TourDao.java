package com.example.touragency.model.dao;

import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.enums.TourCategory;
import com.example.touragency.model.exceptions.DaoException;

import java.math.BigDecimal;
import java.util.List;

public interface TourDao extends GenericDao<Tour>{


    void addTourToOrder(int tourId, int orderId) throws DaoException;

    Tour findById(int id) throws DaoException;

    Tour findByName(String name) throws DaoException;



    List<Tour> findByOrderId(int id) throws DaoException;

    List<Tour> findByCategory(TourCategory category) throws DaoException;

    List<Tour> findByPrice(BigDecimal price) throws DaoException;

    List<Tour> findByPeople(int peopleNumber) throws DaoException;

    List<Tour> findByHotelId(int hotelId) throws DaoException;



}