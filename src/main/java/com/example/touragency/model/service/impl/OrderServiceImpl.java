package com.example.touragency.model.service.impl;

import com.example.touragency.constants.Messages;
import com.example.touragency.controller.Servlet;
import com.example.touragency.model.ConnectionPoolHolder;
import com.example.touragency.model.dao.*;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.entity.*;
import com.example.touragency.model.entity.enums.OrderStatus;
import com.example.touragency.model.service.OrderService;
import com.example.touragency.model.service.ServiceTools;
import com.example.touragency.exceptions.*;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public final static Logger log = Logger.getLogger(Servlet.class);


    @Override
    public List<Order> getByLogin(String login) throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findOrdersByLogin(login);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public int getCount() throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.getCount();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public List<Order> getAll() throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findAll();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    @Override
    public List<Order> getPage(int pageId, int pageSize) throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findByLimit(pageId * pageSize - pageSize + 1, pageSize);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public List<Order> getPageOpenedFirst(int pageId, int pageSize) throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findOrdersByLimitOpenedFirst(pageId * pageSize - pageSize + 1, pageSize);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public List<Order> getPagePaidFirst(int pageId, int pageSize) throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findOrdersByLimitPaidFirst(pageId * pageSize - pageSize + 1, pageSize);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    @Override
    public Optional<Order> getById(int id) throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findById(id);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    @Override
    public void applyForOrder(int tourId, String login) throws ServiceException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TourDao tourDao = daoFactory.createTourDao(connection);
             OrderDao orderDao = daoFactory.createOrderDao(connection);
             DiscountDao discountDao = daoFactory.createDiscountDao(connection);
             UserDao userDao = daoFactory.createUserDao(connection)) {

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            User client = userDao.findUserByLogin(login).get();
            Tour tour = tourDao.findById(tourId).orElseThrow(() -> new ServiceException(Messages.TOUR_DOESNT_EXIST));

            throwExceptionIfTourIsNotAvailable(client, tour);
            tour.setTakenPlaces(tour.getTakenPlaces() + 1);

            List<Order> orders = orderDao.findOrdersByLogin(login);
            BigDecimal price = getPriceWithDiscount(orders, discountDao, tour.getPrice());
            Order order = Order.createOrder(Calendar.getInstance(), OrderStatus.OPENED,
                    client, price, tour.getName(), tour.getId());

            orderDao.create(order);
            tourDao.update(tour);
            connection.commit();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e) {
                log.error(throwables.getMessage());
                throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
            }
        }

    }


    @Override
    public void update(Order order) throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            orderDao.update(order);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public int add(Order entity) throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.create(entity);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    public void cancel(int id) throws ServiceException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (OrderDao orderDao = daoFactory.createOrderDao(connection);
             TourDao tourDao = daoFactory.createTourDao(connection)) {

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            Order order = orderDao.findById(id).orElseThrow(() -> new ServiceException(Messages.ORDER_DOESNT_EXIST));
            order.setStatus(OrderStatus.CANCELED);
            orderDao.update(order);

            Tour tour = tourDao.findById(order.getTourId()).orElseThrow(() -> new ServiceException(Messages.TOUR_DOESNT_EXIST));
            tour.setTakenPlaces(tour.getTakenPlaces() - 1);
            tourDao.update(tour);

            connection.commit();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());

            try {
                connection.rollback();
            } catch (SQLException e) {
                log.error(throwables.getMessage());
                throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
            }
        }
    }

    public void confirmPaid(int id) throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {

            Order order = orderDao.findById(id).orElseThrow(() -> new ServiceException(Messages.ORDER_DOESNT_EXIST));
            order.setStatus(OrderStatus.PAID);
            orderDao.update(order);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public long getOpenedCount() throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findAll()
                    .stream().filter(order -> order.getStatus()
                            .equals(OrderStatus.OPENED)).count();

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    public void setOpened(int id) throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {

            Order order = orderDao.findById(id).orElseThrow(() -> new ServiceException(Messages.ORDER_DOESNT_EXIST));
            order.setStatus(OrderStatus.OPENED);
            orderDao.update(order);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    private BigDecimal getPriceWithDiscount(List<Order> orders, DiscountDao discountDao, BigDecimal tourPrice) throws ServiceException {
        try {
            Discount discount = discountDao.findById(1).get();
            return ServiceTools.getPriceWithDiscount(discount, orders, tourPrice);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    private void throwExceptionIfTourIsNotAvailable(User client, Tour tour) throws ServiceException {
        if (ServiceTools.isUserBlocked(client)) throw new ServiceException(Messages.USER_BLOCKED);
        if (tourIsOutDated(tour)) throw new ServiceException(Messages.TOUR_OUTDATED);
        if (!tourContainsFreeTickets(tour)) throw new ServiceException(Messages.NO_TICKETS);
    }

    private boolean tourIsOutDated(Tour tour) {
        return ServiceTools.isOutDated(tour);
    }

    private boolean tourContainsFreeTickets(Tour tour) {
        return ServiceTools.containsFreeTickets(tour);
    }


}
