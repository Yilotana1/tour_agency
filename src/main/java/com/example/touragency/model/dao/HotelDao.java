package com.example.touragency.model.dao;

import com.example.touragency.model.entity.Hotel;
import com.example.touragency.model.exceptions.DaoException;

public interface HotelDao extends GenericDao<Hotel>{

    Hotel findByName(String name) throws DaoException;
}
