package com.example.touragency.model.service.impl;

import com.example.touragency.ConnectionPoolHolder;
import com.example.touragency.model.dao.*;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.entity.*;
import com.example.touragency.model.entity.enums.OrderStatus;
import com.example.touragency.model.exceptions.DaoException;
import com.example.touragency.model.exceptions.ServiceException;
import com.example.touragency.model.service.OrderService;
import com.example.touragency.model.service.ServiceTools;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public List<Order> getAllOrders() throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getOpenedOrders() throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findAll()
                    .stream()
                    .filter(order -> order.getStatus().equals(OrderStatus.OPENED))
                    .collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getPaidOrders() throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findAll()
                    .stream()
                    .filter(order -> order.getStatus().equals(OrderStatus.PAID))
                    .collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getCanceledOrders() throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findAll()
                    .stream()
                    .filter(order -> order.getStatus().equals(OrderStatus.CANCELED))
                    .collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public Order getOrderById(int id) throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


    @Override
    public void applyForOrder(List<String> tourNames, String clientLogin) throws ServiceException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TourDao tourDao = daoFactory.createTourDao(connection);
             OrderDao orderDao = daoFactory.createOrderDao(connection);
             DiscountDao discountDao = daoFactory.createDiscountDao(connection);
             UserDao userDao = daoFactory.createUserDao(connection)) {

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            User client = userDao.findUserByLogin(clientLogin);
            List<Tour> tours = tourDao.findAll().stream().filter(tour -> tourNames.contains(tour.getName()))
                    .collect(Collectors.toList());

            catchExceptionIfToursNotAvailable(client, tours);

            int userTourNumber = tours.size();

            BigDecimal price = getPriceWithDiscount(tours, discountDao);
            tours.forEach(tour -> tour.setTakenPlaces(tour.getTakenPlaces() + 1));

            Order order = Order.createOrder(Calendar.getInstance(), OrderStatus.OPENED,
                    client, price, userTourNumber);

            int orderId = orderDao.create(order);
            for (Tour tour : tours) {
                tourDao.update(tour);
                tourDao.addTourToOrder(tour.getId(), orderId);
            }
            connection.commit();
        } catch (Exception throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }




    @Override
    public void confirmOrderPaid(int id) throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            Order order = orderDao.findById(id);
            order.setStatus(OrderStatus.PAID);
            orderDao.update(order);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void cancelOrder(int id) throws ServiceException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (OrderDao orderDao = daoFactory.createOrderDao(connection);
             TourDao tourDao = daoFactory.createTourDao(connection)) {
            connection.setAutoCommit(false);
            Order order = orderDao.findById(id);
            order.setStatus(OrderStatus.CANCELED);
            orderDao.update(order);
            List<Tour> tours = tourDao.findByOrderId(order.getId());
            tours.forEach(tour -> tour.setTakenPlaces(tour.getTakenPlaces() - 1));
            for (Tour tour : tours) {
                tourDao.update(tour);
            }
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
    public void removeOrder(int id) throws ServiceException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (OrderDao orderDao = daoFactory.createOrderDao(connection);
             TourDao tourDao = daoFactory.createTourDao(connection)) {

            connection.setAutoCommit(false);

            Order order = orderDao.findById(id);
            orderDao.delete(order.getId());
            List<Tour> tours = tourDao.findByOrderId(order.getId());
            tours.forEach(tour -> tour.setTakenPlaces(tour.getTakenPlaces() - 1));
            for (Tour tour : tours) tourDao.update(tour);

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








    private BigDecimal getPriceWithDiscount(List<Tour> tours, DiscountDao discountDao) throws DaoException {
        Discount discount = discountDao.findById(1);
        return ServiceTools.getPriceWithDiscount(discount, tours);
    }

    private void catchExceptionIfToursNotAvailable(User client, List<Tour> tours) throws ServiceException {
        if (ServiceTools.isUserBlocked(client)) throw new ServiceException("User is blocked");
        if (checkIfToursAreOutDated(tours)) throw new ServiceException("These tours are outdated");
        if (!checkIfToursContainFreeTickets(tours)) throw new ServiceException("This tour doesn't contain free places");
    }

    private boolean checkIfToursAreOutDated(List<Tour> tours) {
        for (Tour tour : tours) {
            if (ServiceTools.isOutDated(tour, Calendar.getInstance())) return true;
        }
        return false;
    }

    private boolean checkIfToursContainFreeTickets(List<Tour> tours) {
        for (Tour tour : tours) {
            if (ServiceTools.containsFreeTickets(tour)) return true;
        }
        return false;
    }



}
