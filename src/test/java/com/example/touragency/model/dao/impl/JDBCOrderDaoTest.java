package com.example.touragency.model.dao.impl;

import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.dao.OrderDao;
import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.enums.OrderStatus;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.entity.enums.UserStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class JDBCOrderDaoTest {

    private static final String DB_NAME = "tour_agency_copy";
    private static final String USER = "root";
    private static final String PASS = "Iamprogrammer1";


    private OrderDao orderDao;

    @Before
    public void setUp() throws Exception {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/"
                        + DB_NAME + "?user="
                        + USER + "&password=" + PASS + "&characterEncoding=utf8&sslMode=DISABLED&serverTimezone=UTC");

        orderDao = DaoFactory.getInstance().createOrderDao(connection);
    }

    @Test
    public void getCount_should_shouldReturnToursSize() throws ServiceException, SQLException {
        int count = orderDao.getCount();
        List<Order> orders = orderDao.findAll();
        assertEquals("Order count should be the same as orders list size", count, orders.size());
    }


    @Test
    public void create_shouldReturnGeneratedId() throws SQLException {
        Order order = Order.createOrder(Calendar.getInstance(), OrderStatus.OPENED,
                User.createUser(1, "test", "test", "test", "test",
                        UserStatus.NON_BLOCKED, "test", "test", Role.CLIENT),
                new BigDecimal("50"), "test", 1);


        int id = orderDao.create(order);
        Assert.assertTrue("Generated id should be more than 0", id > 0);
    }


    @Test
    public void findById_shouldReturnOrder() throws SQLException {
        Optional<Order> order = orderDao.findById(1);
        Assert.assertTrue("Dao should return order", order.isPresent());
    }


    @Test
    public void findById_shouldReturnNull() throws SQLException {
        int id = Integer.MAX_VALUE;
        Optional<Order> order = orderDao.findById(id);
        Assert.assertFalse("Dao must return null", order.isPresent());
    }

    @Test
    public void findAll() throws SQLException {
        List<Order> orders = orderDao.findAll();
        int size = orders.size();
        Assert.assertTrue("Orders size should be more than 0", size > 0);
    }


    @Test
    public void findByLimit() throws SQLException {
        int start = 1;
        int count = 3;
        List<Order> orders = orderDao.findByLimit(start, count);
        assertEquals("Orders size should equals " + count, orders.size(), count);
    }

    @Test
    public void delete_shouldDecreasedOrdersList() throws SQLException {
        List<Order> orders = orderDao.findAll();
        int sizeBefore = orders.size();

        orderDao.delete(2);
        orders = orderDao.findAll();

        int sizeAfter = orders.size();

        Assert.assertTrue("Orders list size after removing should be less", sizeAfter < sizeBefore);
    }

    @Test
    public void findOrdersByLogin_shouldReturnNotEmptyList() throws SQLException {
        String login = "Anton";
        List<Order> orders = orderDao.findOrdersByLogin(login);
        Assert.assertTrue("List size should be more than 0", orders.size() > 0);
    }

    @Test
    public void findOrdersByLimitOpenedFirst_shouldReturnListInRange() throws SQLException {
        int start = 1;
        int count = 2;
        List<Order> orders = orderDao.findOrdersByLimitOpenedFirst(start, count);
        assertEquals("List size should equal" + count, orders.size(), count);
    }

    @Test
    public void findOrdersByLimitPaidFirst_shouldReturnListInRange() throws SQLException {
        int start = 1;
        int count = 2;
        List<Order> orders = orderDao.findOrdersByLimitPaidFirst(start, count);
        assertEquals("List size should equal" + count, orders.size(), count);
    }
}