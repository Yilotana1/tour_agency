package com.example.touragency.model.service.impl;


import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.dao.HotelDao;
import com.example.touragency.model.entity.Hotel;
import com.example.touragency.model.service.HotelService;

import java.sql.SQLException;
import java.util.List;


public class HotelServiceImpl implements HotelService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Hotel getById(int id) {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            return hotelDao.findById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Hotel> getAll()  {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            return hotelDao.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Hotel entity) {

    }

    @Override
    public List<Hotel> getPage(int pageId, int pageSize) {
        return null;
    }



    @Override
    public int add(Hotel hotel) {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            return hotelDao.create(hotel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }


    @Override
    public int getCount() {
        return 0;
    }

}
