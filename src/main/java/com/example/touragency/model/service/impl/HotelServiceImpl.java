package com.example.touragency.model.service.impl;


import com.example.touragency.constants.Messages;
import com.example.touragency.controller.Servlet;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.dao.HotelDao;
import com.example.touragency.model.dao.OrderDao;
import com.example.touragency.model.entity.Hotel;
import com.example.touragency.model.service.HotelService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class HotelServiceImpl implements HotelService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public final static Logger log = Logger.getLogger(Servlet.class);


    @Override
    public Optional<Hotel> getById(int id) throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            return hotelDao.findById(id);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    @Override
    public List<Hotel> getAll() throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            return hotelDao.findAll();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public void update(Hotel entity) {

    }

    @Override
    public List<Hotel> getPage(int pageId, int pageSize) throws ServiceException{
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            return hotelDao.findByLimit(pageId * pageSize - pageSize + 1, pageSize);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }



    @Override
    public int add(Hotel hotel) throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            return hotelDao.create(hotel);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    @Override
    public int getCount() {
        return 0;
    }

}
