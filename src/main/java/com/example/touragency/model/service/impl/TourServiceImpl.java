package com.example.touragency.model.service.impl;

import com.example.touragency.constants.Messages;
import com.example.touragency.controller.Servlet;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.ConnectionPoolHolder;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.dao.HotelDao;
import com.example.touragency.model.dao.TourDao;
import com.example.touragency.model.entity.Hotel;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.enums.TourCategory;
import com.example.touragency.model.entity.enums.TourStatus;
import com.example.touragency.model.service.TourService;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


/**
 * Tour Service implementation. Presents method to realize business process
 */
public class TourServiceImpl implements TourService, Comparator<Tour> {


    public static final int INITIAL_TAKEN_TICKETS = 0;

    DaoFactory daoFactory = DaoFactory.getInstance();

    public final static Logger log = Logger.getLogger(Servlet.class);

    @Override
    public List<Tour> getAll() throws ServiceException{
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findAll();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    @Override
    public void update(Tour tour) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            throwExceptionIfNoTickets(tour, tourDao);
            tourDao.update(tour);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    /**
     * Check if there are available tickets on the certain tour.
     * @param tour
     * @param tourDao
     * @throws ServiceException
     */
    private void throwExceptionIfNoTickets(Tour tour, TourDao tourDao) throws ServiceException {
        Optional<Tour> tourFromDb = null;
        try {
            tourFromDb = tourDao.findById(tour.getId());
            if (tour.getMaxTickets() < tourFromDb.get().getTakenTickets()) {
                throw new ServiceException(Messages.MAX_TICKETS_NOT_LESS_THAN_TAKEN_TICKETS);
            }
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }

    }

    @Override
    public int getCount() throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.getCount();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public Optional<Tour> getById(int id) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findById(id);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public void update(int id, String name, String country, BigDecimal price, int maxTickets, int takenTickets,
                       Calendar startDate, Calendar endDate, TourCategory category, TourStatus status,
                       String hotelName, String city) throws ServiceException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TourDao tourDao = daoFactory.createTourDao(connection);
             HotelDao hotelDao = daoFactory.createHotelDao(connection)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            Hotel hotel = hotelDao.findByName(hotelName).orElseThrow(() -> new ServiceException(Messages.HOTEL_DOESNT_EXIST));

            Tour tour = Tour.createTour(id, name, country, price,
                    maxTickets, takenTickets,
                    startDate, endDate, category, status,
                    hotel, city);

            throwExceptionIfNoTickets(tour, tourDao);

            tourDao.update(tour);

            connection.commit();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    /**
     * Retrieve tour list for pagination in order as it's saved in db. pageId is page number, pageSize is max items on page.
     * @param pageId
     * @param pageSize
     * @return Tour list
     * @throws ServiceException
     */
    @Override
    public List<Tour> getPage(int pageId, int pageSize) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimit(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    /**
     * Retrieve tour list for pagination with specified country. pageId is page number, pageSize is max items on page.
     * @param pageId
     * @param pageSize
     * @return Tour list
     * @throws ServiceException
     */
    @Override
    public List<Tour> getPageCountry(int pageId, int pageSize, String country) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitCountry(pageId * pageSize - pageSize + 1, pageSize, country);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    /**
     * Retrieve tour list for pagination with burning status at the top of the list. pageId is page number, pageSize is max items on page.
     * @param pageId
     * @param pageSize
     * @return Tour list
     * @throws ServiceException
     */
    @Override
    public List<Tour> getPageBurningFirst(int pageId, int pageSize) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitBurningFirst(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public List<Tour> getPageNonBurningFirst(int pageId, int pageSize) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitNonBurningFirst(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    @Override
    public List<Tour> getPageHighHotelStarsFirst(int pageId, int pageSize) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitHighHotelStarsFirst(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public List<Tour> getPageLowHotelStarsFirst(int pageId, int pageSize) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitLowHotelStarsFirst(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public List<Tour> getPageHighPriceFirst(int pageId, int pageSize) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitHighPriceFirst(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public List<Tour> getPageLowPriceFirst(int pageId, int pageSize) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitLowPriceFirst(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public List<Tour> getPageExcursion(int pageId, int pageSize) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitExcursion(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public List<Tour> getPageRest(int pageId, int pageSize) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitRest(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public List<Tour> getPageShopping(int pageId, int pageSize) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitShopping(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    @Override
    public List<Tour> getPageName(int pageId, int pageSize, String name) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findByLimitName(pageId * pageSize - pageSize + 1, pageSize, name);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    @Override
    public void create(String name, String country, BigDecimal price,
                       int maxTickets, Calendar startDate, Calendar endDate, TourCategory category, TourStatus status,
                       String hotelName, String city) throws ServiceException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TourDao tourDao = daoFactory.createTourDao(connection);
             HotelDao hotelDao = daoFactory.createHotelDao(connection)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            Hotel hotel = hotelDao.findByName(hotelName).orElseThrow(() -> new ServiceException(Messages.HOTEL_DOESNT_EXIST));

            tourDao.findByName(name).orElseThrow(() -> new ServiceException(Messages.TOUR_DOESNT_EXIST));

            Tour tour = Tour.createTour(name, country, price, maxTickets,
                    INITIAL_TAKEN_TICKETS, startDate, endDate,
                    category, status, hotel, city);

            tourDao.create(tour);

            connection.commit();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public void deleteByName(String name) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            tourDao.getConnection().setAutoCommit(false);
            tourDao.getConnection().setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            Optional<Tour> tour = tourDao.findByName(name);
            tour.orElseThrow(() -> new ServiceException(Messages.TOUR_DOESNT_EXIST));

            tourDao.delete(tour.get().getId());

            tourDao.getConnection().commit();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public int add(Tour tour) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao();) {
            return tourDao.create(tour);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public void changeStatus(int id, TourStatus status) throws ServiceException {
        try (TourDao tourDao = daoFactory.createTourDao()) {

            Optional<Tour> tour = tourDao.findById(id);
            tour.orElseThrow(() -> new ServiceException(Messages.TOUR_DOESNT_EXIST));
            tour.get().setStatus(status);
            tourDao.update(tour.get());

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    @Override
    public int compare(Tour o1, Tour o2) {
        return (o1.getStatus().getId() - o2.getStatus().getId());
    }
}
