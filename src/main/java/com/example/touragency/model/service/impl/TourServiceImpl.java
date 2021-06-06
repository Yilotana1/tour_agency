package com.example.touragency.model.service.impl;

import com.example.touragency.model.Tools;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.dao.TourDao;
import com.example.touragency.model.dao.TourHotelBeanDao;
import com.example.touragency.model.dao.beans.TourHotelBean;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.TourCategory;
import com.example.touragency.model.entity.TourStatus;
import com.example.touragency.model.exceptions.DaoException;
import com.example.touragency.model.exceptions.ServiceException;
import com.example.touragency.model.service.TourService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TourServiceImpl implements TourService, Comparator<TourHotelBean> {


    DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public void addTour(String name, String country, BigDecimal price,
                        int maxPlaces, int minPlaces, int takenPlaces,
                        Calendar startDate, Calendar endDate, TourCategory category,
                        TourStatus status, int hotel_id, String city) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao();) {
            tourDao.create(Tour.createTour(-1, name, country, price,
                    maxPlaces, minPlaces, takenPlaces,
                    startDate, endDate, category, status,
                    hotel_id, city));

        } catch (DaoException e) {

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void addTour(Tour tour) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao();) {
            tourDao.create(tour);

        } catch (DaoException e) {

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void removeTour(int id) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            tourDao.delete(id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourCountry(int id, String country) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setCountry(country);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourName(int id, String name) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setCountry(name);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourPrice(int id, BigDecimal price) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setPrice(price);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourMaxPlaces(int id, int maxPlaces) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setMaxPlaces(maxPlaces);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourMinPlaces(int id, int minPlaces) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setMaxPlaces(minPlaces);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourStart(int id, Calendar startDate) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setStartDate(startDate);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourEnd(int id, Calendar endDate) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setStartDate(endDate);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourCategory(int id, TourCategory category) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setCategory(category);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourHotel(int id, int hotelId) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setHotelId(hotelId);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeCity(int id, String city) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setCity(city);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourStatus(int id, TourStatus status) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setStatus(status);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<TourHotelBean> getAllTours() throws ServiceException {
        try (TourHotelBeanDao tourHotelBeanDao = daoFactory.createTourHotelBeanDao()) {

            return tourHotelBeanDao.findAll().stream()
                    .sorted(this).collect(Collectors.toList());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TourHotelBean> getToursByCategory(TourCategory category) throws ServiceException {
        try (TourHotelBeanDao tourHotelBeanDao = daoFactory.createTourHotelBeanDao()) {

            return tourHotelBeanDao.findByCategory(category);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public TourHotelBean getTourById(int id) throws ServiceException {
        try (TourHotelBeanDao tourHotelBeanDao = daoFactory.createTourHotelBeanDao()){
            return tourHotelBeanDao.findById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public TourHotelBean getTourByName(String name) throws ServiceException {
        try (TourHotelBeanDao tourHotelBeanDao = daoFactory.createTourHotelBeanDao()){
            return tourHotelBeanDao.findByName(name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TourHotelBean> getToursByPriceMoreThan(BigDecimal price) throws ServiceException {
        try (TourHotelBeanDao tourHotelBeanDao = daoFactory.createTourHotelBeanDao()) {

            return tourHotelBeanDao.findAll().stream().
                    filter(bean -> Tools.decimalCompare(bean.getTour().getPrice(), price)).collect(Collectors.toList());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TourHotelBean> getToursByPriceLessThan(BigDecimal price) throws ServiceException {
        try (TourHotelBeanDao tourHotelBeanDao = daoFactory.createTourHotelBeanDao()) {

            return tourHotelBeanDao.findAll().stream().
                    filter(bean -> !Tools.decimalCompare(bean.getTour().getPrice(), price)).collect(Collectors.toList());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TourHotelBean> getToursByPeopleMoreThan(int peopleNumber) throws ServiceException {
        try (TourHotelBeanDao tourHotelBeanDao = daoFactory.createTourHotelBeanDao()) {

            return tourHotelBeanDao.findAll().stream().
                    filter(bean -> bean.getTour().getMaxPlaces() >= peopleNumber).collect(Collectors.toList());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public List<TourHotelBean> getToursByPeopleLessThan(int peopleNumber) throws ServiceException {
        try (TourHotelBeanDao tourHotelBeanDao = daoFactory.createTourHotelBeanDao()) {

            return tourHotelBeanDao.findAll().stream().
                    filter(bean -> bean.getTour().getMaxPlaces() <= peopleNumber).collect(Collectors.toList());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TourHotelBean> getToursByHotelStarsMoreThan(int stars) throws ServiceException {
        try (TourHotelBeanDao tourHotelBeanDao = daoFactory.createTourHotelBeanDao()) {

            return tourHotelBeanDao.findAll().stream().
                    filter(bean -> bean.getTour().getMaxPlaces() >= stars).collect(Collectors.toList());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TourHotelBean> getToursByHotelStarsLessThan(int stars) throws ServiceException {
        try (TourHotelBeanDao tourHotelBeanDao = daoFactory.createTourHotelBeanDao()) {

            return tourHotelBeanDao.findAll().stream().
                    filter(bean -> bean.getHotel().getStars() <= stars).collect(Collectors.toList());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public int compare(TourHotelBean o1, TourHotelBean o2) {
        return (o1.getTour().getStatus().getId() - o2.getTour().getStatus().getId());
    }
}
