package com.example.touragency.model.service.impl;

import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.dao.HotelDao;
import com.example.touragency.model.entity.Hotel;
import com.example.touragency.model.exceptions.ServiceException;
import com.example.touragency.model.service.HotelService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class HotelServiceImpl implements HotelService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Hotel getHotelById(int id) throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            return hotelDao.findById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Hotel getHotelByName(String name) throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            return hotelDao.findByName(name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Hotel> getAllHotels() throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            return hotelDao.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Hotel> getHotelsWithStarsMoreThanOrEquals(int stars) throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            return hotelDao.findAll()
                    .stream()
                    .filter(hotel -> hotel.getStars() >= stars)
                    .collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Hotel> getHotelsWithStarsLessThanOrEquals(int stars) throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            return hotelDao.findAll()
                    .stream()
                    .filter(hotel -> hotel.getStars() <= stars)
                    .collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Hotel> getHotelsByCity(String city) throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            return hotelDao.findAll()
                    .stream()
                    .filter(hotel -> hotel.getCity().equals(city))
                    .collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int addHotel(String name, int stars, String city, String address, String adminPhone) throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            Hotel hotel = Hotel.createHotel(name, city, address, stars, adminPhone);
            return hotelDao.create(hotel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public void removeHotel(String name) throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            hotelDao.delete(hotelDao.findAll()
                    .stream()
                    .filter(hotel -> hotel.getName().equals(name))
                    .findFirst().get().getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeStars(String name, int stars) throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()){
            Hotel hotel = hotelDao.findByName(name);
            hotel.setStars(stars);
            hotelDao.update(hotel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeName(String oldName, String newName) throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()){
            Hotel hotel = hotelDao.findByName(oldName);
            hotel.setName(newName);
            hotelDao.update(hotel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeAddress(String name, String address) throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()){
            Hotel hotel = hotelDao.findByName(name);
            hotel.setAddress(address);
            hotelDao.update(hotel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeAdminPhone(String name, String adminPhone) throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()){
            Hotel hotel = hotelDao.findByName(name);
            hotel.setAdminPhone(adminPhone);
            hotelDao.update(hotel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeCity(String name, String city) throws ServiceException {
        try (HotelDao hotelDao = daoFactory.createHotelDao()){
            Hotel hotel = hotelDao.findByName(name);
            hotel.setCity(city);
            hotelDao.update(hotel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
