package com.example.touragency.model.service;

import com.example.touragency.model.entity.Hotel;

import java.util.List;

public interface HotelService extends Service<Hotel>{

    Hotel getByName(String name);

    List<Hotel> getWithStarsMoreThanOrEquals(int stars);

    List<Hotel> getWithStarsLessThanOrEquals(int stars);

    List<Hotel> getByCity(String city);

    void remove(String name);

    void changeStars(String name, int stars);

    void changeName(String oldName, String newName);

    void changeAddress(String name, String address);

    void changeAdminPhone(String name, String adminPhone);

    void changeCity(String name, String city);

}
