package com.example.touragency.model.dao;

import com.example.touragency.model.entity.Hotel;

import java.util.Optional;

public interface HotelDao extends GenericDao<Hotel>{

    Optional<Hotel> findByName(String name);
}
