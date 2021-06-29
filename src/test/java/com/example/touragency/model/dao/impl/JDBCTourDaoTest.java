package com.example.touragency.model.dao.impl;

import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.dao.TourDao;
import com.example.touragency.model.entity.Hotel;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.enums.TourCategory;
import com.example.touragency.model.entity.enums.TourStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class JDBCTourDaoTest {

    private static final String DB_NAME = "tour_agency_copy";
    private static final String USER = "root";
    private static final String PASS = "Iamprogrammer1";


    private TourDao tourDao;

    @Before
    public void setUp() throws Exception {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/"
                        + DB_NAME + "?user="
                        + USER + "&password=" + PASS + "&characterEncoding=utf8&sslMode=DISABLED&serverTimezone=UTC");

        tourDao = DaoFactory.getInstance().createTourDao(connection);
    }


    @Test
    public void findAll_shouldReturnNotEmptyList() throws SQLException {
        List<Tour> tours = tourDao.findAll();
        Assert.assertTrue("Tours size should be more than 0", tours.size() > 0);
    }


    @Test
    public void findByLimit_shouldReturnListInRange() throws SQLException {
        int start = 1;
        int count = 3;
        List<Tour> tours = tourDao.findByLimit(start, count);
        assertEquals("Tours size should be equal " + count, tours.size(), count);
    }

    @Test
    public void findByLimitCountry_shouldReturnListInRange() throws SQLException {
        int start = 1;
        int count = 2;
        String country = "Ukraine";
        List<Tour> tours = tourDao.findByLimitCountry(start, count, country);
        assertEquals("Tours size should be equal " + count, tours.size(), count);
    }

    @Test
    public void findByLimitBurning_shouldReturnListInRange() throws SQLException {
        int start = 1;
        int count = 2;
        List<Tour> tours = tourDao.findByLimitBurningFirst(start, count);
        Assert.assertTrue("Tours size must be more than 0", tours.size() > 0);
    }

    @Test
    public void findByLimitHotelStars_shouldReturnTheSameSizeForLists() throws SQLException {
        int start = 1;
        int count = 2;

        List<Tour> toursWithHighStars = tourDao.findByLimitHighHotelStarsFirst(start, count);
        List<Tour> toursWithLowStars = tourDao.findByLimitLowHotelStarsFirst(start, count);
        Assert.assertEquals("High stars should return the same size", toursWithHighStars.size(), toursWithLowStars.size());

    }

    @Test
    public void findByLimitHighHotelStars_shouldReturnListInRange() throws SQLException {
        int start = 1;
        int count = 2;

        List<Tour> tours = tourDao.findByLimitHighHotelStarsFirst(start, count);
        Assert.assertTrue("Tours should be more than 0", tours.size() > 0);

    }

    @Test
    public void delete_shouldDecreasedOrdersList() throws SQLException {
        List<Tour> tours = tourDao.findAll();
        int sizeBefore = tours.size();

        tourDao.delete(2);
        tours = tourDao.findAll();

        int sizeAfter = tours.size();

        Assert.assertTrue("Orders list size after removing should be less", sizeAfter < sizeBefore);
    }


    @Test(expected = SQLException.class)
    public void create_shouldThrowException() throws SQLException {
        Tour tour = Tour.createTour("test tour", "Ukraine", new BigDecimal("300"),
                5, 1, Calendar.getInstance(), Calendar.getInstance(), TourCategory.REST, TourStatus.BURNING,
                Hotel.createHotel(1, "Aria Hotel", "test", "test", 5, "+39080949849"), "Lviv");

        tourDao.create(tour);
        tourDao.create(tour);
    }


    @Test
    public void create_shouldReturnId() throws SQLException {
        Tour tour = Tour.createTour("test tour", "Ukraine", new BigDecimal("300"),
                5, 1, Calendar.getInstance(), Calendar.getInstance(), TourCategory.REST, TourStatus.BURNING,
                Hotel.createHotel(1, "Aria Hotel", "test", "test", 5, "+39080949849"), "Lviv");

        int id = tourDao.create(tour);

        Assert.assertTrue("Should return generated tour id", id > 0);
    }


    @Test
    public void findById_shouldReturnRespectiveTour() throws SQLException {
        int id = 1;
        Optional<Tour> tour = tourDao.findById(id);
        Assert.assertTrue("Dao should return tour", tour.isPresent());
    }

    @Test
    public void findById_shouldReturnNull() throws SQLException {
        int id = Integer.MAX_VALUE;
        Optional<Tour> tour = tourDao.findById(id);
        Assert.assertFalse("Dao should return tour", tour.isPresent());
    }


    @Test
    public void findByName_shouldReturnRespectiveTour() throws SQLException {
        String name = "private full day tour";

        Optional<Tour> tour = tourDao.findByName(name);
        Assert.assertTrue("Dao should return tour", tour.isPresent());
    }

    @Test
    public void findByName_shouldReturnNull() throws SQLException {
        String name = "ERROR";
        Optional<Tour> tour = tourDao.findByName(name);
        Assert.assertFalse("Dao should return tour", tour.isPresent());
    }

    @Test
    public void update_shouldUpdateFirstName() throws SQLException {
        String name = "Exciting weekends with all family";
        String newName = "TEST";
        Optional<Tour> tour = tourDao.findByName(name);
        tour.get().setName(newName);
        tourDao.update(tour.get());
        Optional<Tour> updatedTour = tourDao.findByName(newName);
        Assert.assertNotEquals("Name should be updated", name, updatedTour.get().getName());
    }

    @Test(expected = SQLException.class)
    public void update_shouldThrowException() throws SQLException {
        String name = "Exciting weekends with all family";
        String newName = "Amazing new year celebrating";
        Optional<Tour> tour = tourDao.findByName(name);
        tour.get().setName(newName);
        tourDao.update(tour.get());
    }


}