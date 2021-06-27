package com.example.touragency.model.service.impl;


import com.example.touragency.constants.ErrorMessages;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.dao.HotelDao;
import com.example.touragency.model.entity.Hotel;
import com.example.touragency.model.service.HotelService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class HotelServiceImpl implements HotelService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Optional<Hotel> getById(int id) throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            return hotelDao.findById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new ServiceException(ErrorMessages.UNDEFINED_EXCEPTION);
        }
    }


    @Override
    public List<Hotel> getAll() throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            return hotelDao.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new ServiceException(ErrorMessages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public void update(Hotel entity) {

    }

    @Override
    public List<Hotel> getPage(int pageId, int pageSize) {
        return null;
    }



    @Override
    public int add(Hotel hotel) throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            return hotelDao.create(hotel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new ServiceException(ErrorMessages.UNDEFINED_EXCEPTION);
        }
    }


    @Override
    public int getCount() {
        return 0;
    }

}
