package com.example.touragency.model.dao.impl;

import com.example.touragency.model.entity.Hotel;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.dao.HotelDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JDBCHotelDaoTest {

    private static final String DB_NAME = "tour_agency_copy";
    private static final String USER = "root";
    private static final String PASS = "Iamprogrammer1";


    private HotelDao hotelDao;

    @Before
    public void setUp() throws Exception {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/"
                        + DB_NAME + "?user="
                        + USER + "&password=" + PASS + "&characterEncoding=utf8&sslMode=DISABLED&serverTimezone=UTC");

        hotelDao = DaoFactory.getInstance().createHotelDao(connection);
    }


    @Test
    public void getCount_shouldReturnHotelsNumber() throws SQLException {
        int count = hotelDao.getCount();
        List<Hotel> hotels = hotelDao.findAll();
        Assert.assertEquals("Count should be equal hotels size", count, hotels.size());
    }

    @Test
    public void create_shouldReturnGeneratedId() throws SQLException {
        Hotel hotel = Hotel.createHotel("TEST", "TEST", "TEST", 5, "TEST");
        int id = hotelDao.create(hotel);
        Assert.assertTrue("Dao should return generated id > 0", id > 0);
    }

    @Test(expected = SQLException.class)
    public void create_shouldThrowException() throws SQLException {
        Hotel hotel = Hotel.createHotel("TEST", "TEST", "TEST", 5, "TEST");
        hotelDao.create(hotel);
        hotelDao.create(hotel);
    }

    @Test
    public void findById_shouldReturnHotelWithRespectiveId() throws SQLException {
        int id = 1;
        Optional<Hotel> hotel = hotelDao.findById(id);
        Assert.assertTrue("Dao should return hotel", hotel.isPresent());
    }

    @Test
    public void findByName_shouldReturnHotelWithRespectiveName() throws SQLException {
        String name = "Ancient Castle Park Hotel";
        Optional<Hotel> hotel = hotelDao.findByName(name);
        Assert.assertTrue("Dao should return hotel", hotel.isPresent());
    }

    @Test
    public void findByName_shouldReturnNull() throws SQLException {
        String name = "ERROR";
        Optional<Hotel> hotel = hotelDao.findByName(name);
        Assert.assertFalse("Dao should return hotel", hotel.isPresent());
    }


    @Test
    public void findAll() throws SQLException {
        List<Hotel> hotels = hotelDao.findAll();
        Assert.assertTrue("Hotels list size should be more than 0", hotels.size() > 0);
    }


    //TODO
//    @Test
//    public void findByLimit() throws SQLException {
//        int start = 1;
//        int count = 2;
//        List<Hotel> hotels = hotelDao.findByLimit(start, count);
//        Assert.assertEquals("Hotels list size should be equal " + count, count, hotels.size());
//
//    }

    @Test
    public void update() throws SQLException {
        String name = "Turin Palace Hotel";
        int newStars = 5;

        Optional<Hotel> hotel = hotelDao.findByName(name);
        hotel.get().setStars(newStars);
        hotelDao.update(hotel.get());

        Optional<Hotel> updatedHotel = hotelDao.findByName(name);
        Assert.assertEquals("Stars should be changed", newStars, updatedHotel.get().getStars());

    }

    @Test
    public void delete() throws SQLException {

        Hotel hotel = Hotel.createHotel("TEST1", "TEST1", "TEST1", 5, "TEST1");
        int id = hotelDao.create(hotel);

        int sizeBefore = hotelDao.findAll().size();
        hotelDao.delete(id);
        int sizeAfter = hotelDao.findAll().size();

        Assert.assertTrue("Size after deleting should be less than before", sizeBefore > sizeAfter);

    }
}