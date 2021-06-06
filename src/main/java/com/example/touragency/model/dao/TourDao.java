package com.example.touragency.model.dao;

import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.TourCategory;
import com.example.touragency.model.exceptions.DaoException;

import java.math.BigDecimal;
import java.util.List;

public interface TourDao extends GenericDao<Tour>{

    Tour findById(int id) throws DaoException;

    Tour findByName(String name) throws DaoException;

    List<Tour> findByCategory(TourCategory category) throws DaoException;

    List<Tour> findByPrice(BigDecimal price) throws DaoException;

    List<Tour> findByPeople(int peopleNumber) throws DaoException;

    List<Tour> findByHotelId(int hotelId) throws DaoException;
}
