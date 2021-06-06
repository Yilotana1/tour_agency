package com.example.touragency.model.service.impl;

import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.dao.TourDao;
import com.example.touragency.model.dao.beans.TourHotelBean;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.TourCategory;
import com.example.touragency.model.exceptions.ServiceException;
import com.example.touragency.model.service.TourService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class TourServiceImpl implements TourService {


    DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public List<TourHotelBean> getToursByCategory(TourCategory category) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()){
            List<Tour> tours = tourDao.findByCategory(category);



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    @Override
    public List<TourHotelBean> getToursByPrice(BigDecimal price) throws ServiceException {
        return null;
    }

    @Override
    public List<TourHotelBean> getToursByPeople(int peopleNumber) throws ServiceException {
        return null;
    }

    @Override
    public List<TourHotelBean> getToursByHotelType(int type) throws ServiceException {
        return null;
    }
}
