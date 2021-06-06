package com.example.touragency.model.dao;

import com.example.touragency.model.dao.beans.TourHotelBean;
import com.example.touragency.model.entity.TourCategory;
import com.example.touragency.model.exceptions.DaoException;

import java.util.List;

public interface TourHotelBeanDao extends GenericDao<TourHotelBean> {
    public List<TourHotelBean> findByCategory(TourCategory category) throws DaoException;

    public TourHotelBean findByName(String name) throws DaoException;
}

