package com.example.touragency.model.service;

import com.example.touragency.model.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel getHotelById(int id);

    Hotel getHotelByName(String name);

    List<Hotel> getAllHotels();

    List<Hotel> getHotelsWithStarsMoreThanOrEquals(int stars);

    List<Hotel> getHotelsWithStarsLessThanOrEquals(int stars);

    List<Hotel> getHotelsByCity(String city);

    int addHotel(String name, int stars, String city, String address, String adminPhone);

    void removeHotel(String name);

    void changeStars(String name, int stars);

    void changeName(String oldName, String newName);

    void changeAddress(String name, String address);

    void changeAdminPhone (String name, String adminPhone);

    void changeCity(String name, String city);

}
