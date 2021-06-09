package com.example.touragency.model.service.impl;

import com.example.touragency.model.dao.*;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.dao.beans.OrderClientBean;
import com.example.touragency.model.dao.beans.OrderTourBean;
import com.example.touragency.model.entity.*;
import com.example.touragency.model.exceptions.ServiceException;
import com.example.touragency.model.service.OrderService;
import com.example.touragency.model.service.ServiceTools;

import java.math.BigDecimal;
import java.net.CacheRequest;
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
    public List<OrderClientBean> getAllOrderClientBeans() throws ServiceException {
        try (OrderClientBeanDao orderClientBeanDao = daoFactory.createOrderClientBeanDao()) {
            return orderClientBeanDao.findAll();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderTourBean> getAllOrderTourBeans() throws ServiceException {
        try (OrderTourBeanDao orderTourBeanDao = daoFactory.createOrderTourBeanDao()) {
            return orderTourBeanDao.findAll();

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
    public OrderClientBean getOrderClientBeanById(int id) throws ServiceException {
        try (OrderClientBeanDao orderClientBeanDao = daoFactory.createOrderClientBeanDao()) {
            return orderClientBeanDao.findById(id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public OrderTourBean getOrderTourBeanById(int id) throws ServiceException {
        try (OrderTourBeanDao orderTourBeanDao = daoFactory.createOrderTourBeanDao()) {
            return orderTourBeanDao.findById(id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderTourBean> getOrderTourBeansByClientId(int id) throws ServiceException {
        try (OrderTourBeanDao orderTourBeanDao = daoFactory.createOrderTourBeanDao()) {
            return orderTourBeanDao.findAll().stream()
                    .filter(bean -> bean.getOrder().getClientId() == id)
                    .collect(Collectors.toList());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderTourBean> getOrderTourBeansByClientLogin(String login) throws ServiceException {
        try (OrderTourBeanDao orderTourBeanDao = daoFactory.createOrderTourBeanDao();
             UserDao userDao = daoFactory.createUserDao()) {

            User client = userDao.findAll().stream()
                    .filter(user -> user.getLogin().equals(login))
                    .findFirst().get();

            return orderTourBeanDao.findAll().stream()
                    .filter(bean -> bean.getOrder().getClientId() == client.getId())
                    .collect(Collectors.toList());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void applyForOrder(Tour tour, User client) throws ServiceException {
        if (ServiceTools.isUserBlocked(client)) {
            throw new ServiceException("User is blocked");
        }
        if (ServiceTools.isOutDated(tour, Calendar.getInstance())) {
            throw new ServiceException("This tour is outdated");
        }
        if (!ServiceTools.containsFreePlaces(tour)) {
            throw new ServiceException("This tour doesn't contain free places");
        }
        try (TourDao tourDao = daoFactory.createTourDao();
             OrderDao orderDao = daoFactory.createOrderDao();
             DiscountDao discountDao = daoFactory.createDiscountDao()) {

            long userTourNumber = ServiceTools.countClientOrders(orderDao, client);

            Discount discount = discountDao.findById(1);
            BigDecimal price = ServiceTools.getPriceWithDiscount(discount, tour, userTourNumber);
            tour.setTakenPlaces(tour.getTakenPlaces() + 1);

            Order order = Order.createOrder(-1, tour.getId(), Calendar.getInstance(), OrderStatus.OPENED,
                    client.getId(), price);

            orderDao.create(order);
            tourDao.update(tour);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
        try (OrderDao orderDao = daoFactory.createOrderDao();
             TourDao tourDao = daoFactory.createTourDao()) {
            order.setStatus(OrderStatus.CANCELED);
            orderDao.update(order);
            Tour tour = tourDao.findById(order.getTourId());
            tour.setTakenPlaces(tour.getTakenPlaces() - 1);
            tourDao.update(tour);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void removeOrder(Order order) throws ServiceException {
        try (OrderDao orderDao = daoFactory.createOrderDao();
             TourDao tourDao = daoFactory.createTourDao()) {
            orderDao.delete(order.getId());
            Tour tour = tourDao.findById(order.getTourId());
            tour.setTakenPlaces(tour.getTakenPlaces() - 1);
            tourDao.update(tour);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
