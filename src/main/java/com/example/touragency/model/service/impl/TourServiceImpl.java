package com.example.touragency.model.service.impl;

import com.example.touragency.model.ConnectionPoolHolder;
import com.example.touragency.model.Tools;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.dao.HotelDao;
import com.example.touragency.model.dao.TourDao;
import com.example.touragency.model.entity.Hotel;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.enums.TourCategory;
import com.example.touragency.model.entity.enums.TourStatus;
import com.example.touragency.model.service.TourService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TourServiceImpl implements TourService, Comparator<Tour> {


    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public List<Tour> getToursSortedByStatus() {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findAll()
                    .stream().sorted(this)
                    .collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Tour getTourById(int id) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Tour getTourByName(String name) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByName(name);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tour> getToursWithPriceLessThan(BigDecimal price) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findAll().stream()
                    .filter(tour -> Tools.decimalCompare(price, tour.getPrice()))
                    .collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Tour> getToursWithPriceMoreThan(BigDecimal price) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findAll().stream()
                    .filter(tour -> Tools.decimalCompare(tour.getPrice(), price))
                    .collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Tour> getToursWithPeopleMoreThan(int maxPlaces) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findAll().stream()
                    .filter(tour -> tour.getMaxPlaces() >= maxPlaces)
                    .collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Tour> getToursWithPeopleLessThan(int maxPlaces) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findAll().stream()
                    .filter(tour -> tour.getMaxPlaces() <= maxPlaces)
                    .collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Tour> getToursWithHotelStarsMoreThan(int stars) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findAll().stream()
                    .filter(tour -> tour.getHotel().getStars() >= stars)
                    .collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Tour> getToursWithHotelStarsLessThan(int stars) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findAll().stream()
                    .filter(tour -> tour.getHotel().getStars() <= stars)
                    .collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Tour> getToursByCategory(TourCategory category) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByCategory(category);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Tour> getToursByCountry(String country) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findAll()
                    .stream()
                    .filter(tour -> tour.getCountry().equals(country))
                    .collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


    @Override
    public void addTour(Tour tour) {
        try (TourDao tourDao = daoFactory.createTourDao();) {
            tourDao.create(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void removeTour(int id)  {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            tourDao.delete(id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourCountry(int id, String country) {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setCountry(country);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourName(int id, String name) {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setName(name);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourPrice(int id, BigDecimal price) {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setPrice(price);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourMaxPlaces(int id, int maxPlaces) {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            int minPlaces = tour.getMinPlaces();

            if (maxPlaces < minPlaces) {
            }

            tour.setMaxPlaces(maxPlaces);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourMinPlaces(int id, int minPlaces) {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            int maxPlaces = tour.getMaxPlaces();

            if (minPlaces > maxPlaces) {
            }

            tour.setMinPlaces(minPlaces);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourStart(int id, Calendar startDate){
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            Calendar endDate = tour.getEndDate();

            if (startDate.after(endDate)) {
            }

            tour.setStartDate(startDate);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourEnd(int id, Calendar endDate) {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);

            Calendar startDate = tour.getEndDate();

            if (endDate.before(startDate)) {
            }

            tour.setEndDate(endDate);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourCategory(int id, TourCategory category) {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setCategory(category);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourHotel(int id, String hotelName){
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TourDao tourDao = daoFactory.createTourDao(connection);
             HotelDao hotelDao = daoFactory.createHotelDao(tourDao.getConnection())) {
            connection.setAutoCommit(true);
            Hotel hotel = hotelDao
                    .findAll()
                    .stream()
                    .filter(h -> h.getName()
                            .equals(hotelName)).findFirst().get();

            Tour tour = tourDao.findById(id);
            tour.setHotel(hotel);
            tourDao.update(tour);
            connection.commit();
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourCity(int id, String city) {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setCity(city);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeTourStatus(int id, TourStatus status) {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setStatus(status);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public int compare(Tour o1, Tour o2) {
        return (o1.getStatus().getId() - o2.getStatus().getId());
    }
}
