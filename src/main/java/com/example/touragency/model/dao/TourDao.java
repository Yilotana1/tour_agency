package com.example.touragency.model.dao;

import com.example.touragency.model.entity.Tour;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TourDao extends GenericDao<Tour>{



    Optional<Tour> findById(int id) throws SQLException;

    Optional<Tour> findByName(String name) throws SQLException;

    List<Tour> findByLimitName(int start, int count, String name) throws SQLException;

    List<Tour> findByLimitCountry(int start, int count, String country) throws SQLException;

    List<Tour> findByLimit(int start, int count) throws SQLException;

    List<Tour> findByLimitBurningFirst(int start, int count) throws SQLException;

    List<Tour> findByLimitNonBurningFirst(int start, int count) throws SQLException;

    List<Tour> findByLimitHighHotelStarsFirst(int start, int count) throws SQLException;

    List<Tour> findByLimitLowHotelStarsFirst(int start, int count) throws SQLException;

    List<Tour> findByLimitHighPriceFirst(int start, int count) throws SQLException;

    List<Tour> findByLimitLowPriceFirst(int start, int count) throws SQLException;

    List<Tour> findByLimitExcursion(int start, int count) throws SQLException;

    List<Tour> findByLimitRest(int start, int count) throws SQLException;

    List<Tour> findByLimitShopping(int start, int count) throws SQLException;

}
