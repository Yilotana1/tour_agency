package com.example.touragency.model.service.impl;

import com.example.touragency.model.ConnectionPoolHolder;
import com.example.touragency.model.dao.*;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.entity.*;
import com.example.touragency.model.entity.enums.OrderStatus;
import com.example.touragency.model.service.OrderService;
import com.example.touragency.model.service.ServiceTools;
import com.example.touragency.exceptions.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public List<Order> getByClientId(int clientId) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findOrdersByClientId(clientId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getByLogin(String login) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findOrdersByLogin(login);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.getCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Order> getAll() {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public void changeStatus(int id, OrderStatus status){
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            orderDao.getConnection().setAutoCommit(false);
            orderDao.getConnection().setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            Order order  = orderDao.findById(id);
            order.setStatus(status);
            orderDao.update(order);
            orderDao.getConnection().commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


//    public void update(int id, User client, String tourName, int tourId,
//                       Calendar date, OrderStatus status, BigDecimal price) {
//
//        try (OrderDao orderDao = daoFactory.createOrderDao()) {
//            Order order = Order.createOrder(id, date, status, client, price, tourName, tourId);
//            orderDao.update(order);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }


    @Override
    public void update() throws ServiceException {

    }

    @Override
    public List<Order> getPage(int pageId, int pageSize) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findByLimit(pageId * pageSize - pageSize + 1, pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getPageOpenedFirst(int pageId, int pageSize) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findOrdersByLimitOpenedFirst(pageId * pageSize - pageSize + 1, pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getPagePaidFirst(int pageId, int pageSize) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findOrdersByLimitPaidFirst(pageId * pageSize - pageSize + 1, pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Order> getOpened() {
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
    public List<Order> getPaid() {
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
    public List<Order> getCanceled() {
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
    public Order getById(int id) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


    @Override
    public void applyForOrder(int tourId, int clientId) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TourDao tourDao = daoFactory.createTourDao(connection);
             OrderDao orderDao = daoFactory.createOrderDao(connection);
             DiscountDao discountDao = daoFactory.createDiscountDao(connection);
             UserDao userDao = daoFactory.createUserDao(connection)) {

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            User client = userDao.findById(clientId);
            Tour tour = tourDao.findById(tourId);

            throwExceptionIfTourIsNotAvailable(client, tour);
            tour.setTakenPlaces(tour.getTakenPlaces() + 1);

            List<Order> orders = orderDao.findOrdersByClientId(clientId);
            BigDecimal price = getPriceWithDiscount(orders, discountDao, tour.getPrice());

            Order order = Order.createOrder(Calendar.getInstance(), OrderStatus.OPENED,
                    client, price, tour.getName(), tour.getId());

            int orderId = orderDao.create(order);
            tourDao.update(tour);
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
    public void confirmPaid(int id) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            Order order = orderDao.findById(id);
            order.setStatus(OrderStatus.PAID);
            orderDao.update(order);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Order order) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            orderDao.update(order);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void cancel(int id) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (OrderDao orderDao = daoFactory.createOrderDao(connection);
             TourDao tourDao = daoFactory.createTourDao(connection)) {
            connection.setAutoCommit(false);
            Order order = orderDao.findById(id);
            order.setStatus(OrderStatus.CANCELED);
            orderDao.update(order);
            Tour tour = tourDao.findById(order.getTourId());
            tour.setTakenPlaces(tour.getTakenPlaces() - 1);
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
    public int add(Order entity) {
        return 0;
    }

    @Override
    public void remove(int id) {
    }


//    @Override
//    public void removeOrder(int id) {
//        Connection connection = ConnectionPoolHolder.getConnection();
//        try (OrderDao orderDao = daoFactory.createOrderDao(connection);
//             TourDao tourDao = daoFactory.createTourDao(connection)) {
//
//            connection.setAutoCommit(false);
//
//            Order order = orderDao.findById(id);
//            orderDao.delete(order.getId());
//            List<Tour> tours = tourDao.findByOrderId(order.getId());
//            tours.forEach(tour -> tour.setTakenPlaces(tour.getTakenPlaces() - 1));
//            for (Tour tour : tours) tourDao.update(tour);
//
//            connection.commit();
//        } catch (SQLException throwables) {
//            try {
//                connection.rollback();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            throwables.printStackTrace();
//        }
//    }


    private BigDecimal getPriceWithDiscount(List<Order> orders, DiscountDao discountDao, BigDecimal tourPrice) throws DaoException {
        Discount discount = discountDao.findById(1);
        return ServiceTools.getPriceWithDiscount(discount, orders, tourPrice);
    }

    private void throwExceptionIfTourIsNotAvailable(User client, Tour tour) throws ServiceException {
        if (ServiceTools.isUserBlocked(client)) throw new ServiceException("User is blocked");
        if (tourIsOutDated(tour)) throw new ServiceException("These tours are outdated");
        if (!tourContainsFreeTickets(tour)) throw new ServiceException("This tour doesn't contain free places");
    }

    private boolean tourIsOutDated(Tour tour) {
        return ServiceTools.isOutDated(tour, Calendar.getInstance());
    }

    private boolean tourContainsFreeTickets(Tour tour) {
        return ServiceTools.containsFreeTickets(tour);
    }


}
