package com.example.touragency.model.service.impl;

import com.example.touragency.ConnectionPoolHolder;
import com.example.touragency.model.dao.*;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.entity.*;
import com.example.touragency.model.entity.enums.OrderStatus;
import com.example.touragency.model.exceptions.ServiceException;
import com.example.touragency.model.service.OrderService;
import com.example.touragency.model.service.ServiceTools;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

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
    public Order getOrderById(int id) throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


    @Override
    public void applyForOrder(String tourName, String clientLogin) throws ServiceException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TourDao tourDao = daoFactory.createTourDao(connection);
             OrderDao orderDao = daoFactory.createOrderDao(connection);
             DiscountDao discountDao = daoFactory.createDiscountDao(connection);
             UserDao userDao = daoFactory.createUserDao(connection)) {

            connection.setAutoCommit(false);
            User client = userDao.findAll().stream().filter(c -> c.getLogin().equals(clientLogin)).findFirst().get();
            Tour tour = tourDao.findByName(tourName);

            if (ServiceTools.isUserBlocked(client)) throw new ServiceException("User is blocked");
            if (ServiceTools.isOutDated(tour, Calendar.getInstance())) throw new ServiceException("This tour is outdated");
            if (!ServiceTools.containsFreePlaces(tour))throw new ServiceException("This tour doesn't contain free places");

            long userTourNumber = ServiceTools.countClientOrders(orderDao, client);

            Discount discount = discountDao.findById(1);
            BigDecimal price = ServiceTools.getPriceWithDiscount(discount, tour, userTourNumber);
            tour.setTakenPlaces(tour.getTakenPlaces() + 1);
            Order order = Order.createOrder(-1, Calendar.getInstance(), OrderStatus.OPENED,
                    client, price, (int) userTourNumber);

            orderDao.create(order);
            tourDao.update(tour);
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void confirmOrderPaid(Order order) throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao();) {
            order.setStatus(OrderStatus.PAID);
            orderDao.update(order);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void cancelOrder(Order order) throws ServiceException {
//        try (OrderDao orderDao = daoFactory.createOrderDao();
//             TourDao tourDao = daoFactory.createTourDao()) {
//            order.setStatus(OrderStatus.CANCELED);
//            orderDao.update(order);
//            Tour tour = tourDao.findById(order.getTourId());
//            tour.setTakenPlaces(tour.getTakenPlaces() - 1);
//            tourDao.update(tour);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }


    @Override
    public void removeOrder(Order order) throws ServiceException {
//        try (OrderDao orderDao = daoFactory.createOrderDao();
//             TourDao tourDao = daoFactory.createTourDao()) {
//            orderDao.delete(order.getId());
//            Tour tour = tourDao.findById(order.getTourId());
//            tour.setTakenPlaces(tour.getTakenPlaces() - 1);
//            tourDao.update(tour);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }


}
