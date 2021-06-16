package com.example.touragency.model.service;

import com.example.touragency.model.entity.Hotel;
import com.example.touragency.model.exceptions.ServiceException;

import java.util.List;

public interface HotelService {

    Hotel getHotelById(int id) throws ServiceException;

    Hotel getHotelByName(String name) throws ServiceException;

    List<Hotel> getAllHotels() throws ServiceException;

    List<Hotel> getHotelsWithStarsMoreThanOrEquals(int stars) throws ServiceException;

    List<Hotel> getHotelsWithStarsLessThanOrEquals(int stars) throws ServiceException;

    List<Hotel> getHotelsByCity(String city) throws ServiceException;

    int addHotel(String name, int stars, String city, String address, String adminPhone) throws ServiceException;

    void removeHotel(String name) throws ServiceException;

    void changeStars(String name, int stars) throws ServiceException;

    void changeName(String oldName, String newName) throws ServiceException;

    void changeAddress(String name, String address) throws ServiceException;

    void changeAdminPhone (String name, String adminPhone) throws ServiceException;

    void changeCity(String name, String city) throws ServiceException;

}
