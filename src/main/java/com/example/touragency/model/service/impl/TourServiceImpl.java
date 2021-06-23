package com.example.touragency.model.service.impl;

import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.ConnectionPoolHolder;
import com.example.touragency.Tools;
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
    public List<Tour> getSortedByStatus() {
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
    public List<Tour> getAll() {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void update() throws ServiceException {

    }

    @Override
    public void update(Tour tour) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            checkTourIsCorrect(tour, tourDao);
            tourDao.update(tour);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private void checkTourIsCorrect(Tour tour, TourDao tourDao) throws ServiceException {
        Tour tourFromDb = tourDao.findById(tour.getId());
        if (tour.getMaxPlaces() < tourFromDb.getMinPlaces() || tour.getMaxPlaces() < tourFromDb.getTakenPlaces()) {
            throw new ServiceException("Max-tickets cannot be less than min-tickets or taken tickets");
        }

        if (tour.getMinPlaces() > tourFromDb.getMaxPlaces()) {
            throw new ServiceException("Min-tickets cannot be more than max-tickets");
        }
    }

    @Override
    public int getCount() {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.getCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public Tour getById(int id) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Tour getByName(String name) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByName(name);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(int id, String name, String country, BigDecimal price, int maxTickets, int minTickets, int takenTickets,
                       Calendar startDate, Calendar endDate, TourCategory category, TourStatus status,
                       String hotelName, String city) throws ServiceException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TourDao tourDao = daoFactory.createTourDao(connection);
             HotelDao hotelDao = daoFactory.createHotelDao(connection)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            Hotel hotel = hotelDao.findByName(hotelName);
            if (hotel == null) throw new ServiceException("Hotel with specified name doesn't exist in database");

            Tour tour = Tour.createTour(id, name, country, price,
                    maxTickets, minTickets, takenTickets,
                    startDate, endDate, category, status,
                    hotel, city);

            checkTourIsCorrect(tour, tourDao);

            tourDao.update(tour);

            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public List<Tour> getPage(int pageId, int pageSize) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimit(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tour> getPageCountry(int pageId, int pageSize, String country) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitCountry(pageId * pageSize - pageSize + 1, pageSize, country);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tour> getPageBurningFirst(int pageId, int pageSize) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitBurningFirst(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tour> getPageNonBurningFirst(int pageId, int pageSize) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitNonBurningFirst(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tour> getPageHighHotelStarsFirst(int pageId, int pageSize) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitHighHotelStarsFirst(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tour> getPageLowHotelStarsFirst(int pageId, int pageSize) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitLowHotelStarsFirst(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tour> getPageHighPriceFirst(int pageId, int pageSize) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitHighPriceFirst(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tour> getPageLowPriceFirst(int pageId, int pageSize) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitLowPriceFirst(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tour> getPageExcursion(int pageId, int pageSize) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitExcursion(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tour> getPageRest(int pageId, int pageSize) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitRest(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tour> getPageShopping(int pageId, int pageSize) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitShopping(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tour> getWithPriceLessThan(BigDecimal price) {
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
    public List<Tour> getWithPriceMoreThan(BigDecimal price) {
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
    public List<Tour> getWithPeopleMoreThan(int maxPlaces) {
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
    public List<Tour> getWithPeopleLessThan(int maxPlaces) {
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
    public List<Tour> getWithHotelStarsMoreThan(int stars) {
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
    public List<Tour> getWithHotelStarsLessThan(int stars) {
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
    public List<Tour> getByCategory(TourCategory category) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByCategory(category);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Tour> getByCountry(String country) {
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
    public int add(Tour tour) {
        try (TourDao tourDao = daoFactory.createTourDao();) {
            return tourDao.create(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;

    }

    @Override
    public void remove(int id) {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            tourDao.delete(id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeCountry(int id, String country) {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setCountry(country);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeName(int id, String name) {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setName(name);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changePrice(int id, BigDecimal price) {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setPrice(price);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeMaxPlaces(int id, int maxPlaces) {
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
    public void changeMinPlaces(int id, int minPlaces) {
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
    public void changeStartDate(int id, Calendar startDate) {
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
    public void changeEndDate(int id, Calendar endDate) {
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
    public void changeCategory(int id, TourCategory category) {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setCategory(category);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeHotel(int id, String hotelName) {
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
    public void changeCity(int id, String city) {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Tour tour = tourDao.findById(id);
            tour.setCity(city);
            tourDao.update(tour);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeStatus(int id, TourStatus status) {
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
