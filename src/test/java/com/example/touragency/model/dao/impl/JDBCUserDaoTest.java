package com.example.touragency.model.dao.impl;

import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.dao.UserDao;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.entity.enums.UserStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;


public class JDBCUserDaoTest {

    private UserDao userDao;

    private static final String DB_NAME = "tour_agency_copy";
    private static final String USER = "root";
    private static final String PASS = "Iamprogrammer1";


    @Before
    public void setUp() throws SQLException, FileNotFoundException {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/"
                        + DB_NAME + "?user="
                        + USER + "&password=" + PASS + "&characterEncoding=utf8&sslMode=DISABLED&serverTimezone=UTC");

        userDao = DaoFactory.getInstance().createUserDao(connection);
    }

    @Test
    public void getCount_should_shouldReturnUserSize() throws SQLException {
        int count = userDao.getCount();
        List<User> users = userDao.findAll();
        assertEquals("Order count should be the same as orders list size", count, users.size());
    }


    @Test
    public void findAll_shouldReturnNotEmptyList() throws SQLException {
        List<User> users = userDao.findAll();
        Assert.assertTrue("list size should be more than 0", users.size() > 0);
    }


    @Test
    public void create_shouldReturnId() throws SQLException {
        User user = User.createUser("John", "Doe", "+380938909084", "John@gmail.com",
                UserStatus.NON_BLOCKED, "john1", "532321513", Role.CLIENT);

        int id = userDao.create(user);
        Assert.assertTrue("user created", id > 0);

    }

    @Test(expected = SQLException.class)
    public void create_shouldThrowException() throws SQLException {
        User user = User.createUser("John", "Doe", "+380938909074", "Ivan@gmail.com",
                UserStatus.NON_BLOCKED, "Ivan", "532321513", Role.CLIENT);
        userDao.create(user);
        userDao.create(user);
    }


    @Test
    public void findById_shouldReturnUserWithRespectiveId() throws SQLException {
        int id = 1;
        Optional<User> user = userDao.findById(id);
        Assert.assertTrue("Dao should return existing user", user.isPresent());
    }

    @Test
    public void findById_shouldReturnNull() throws SQLException {
        int id = 100;
        Optional<User> user = userDao.findById(id);
        Assert.assertFalse("Dao should return null if user doesn't exist", user.isPresent());
    }

    @Test
    public void findByLogin_shouldReturnUserWithRespectiveLogin() throws SQLException {
        String login = "Nick123";
        Optional<User> user = userDao.findUserByLogin(login);
        Assert.assertTrue("Dao should return existing user", user.isPresent());
    }

    @Test
    public void findByLogin_shouldReturnNull() throws SQLException {
        String login = "ERROR";
        Optional<User> user = userDao.findUserByLogin(login);
        Assert.assertFalse("Dao should return null if user doesn't exist", user.isPresent());
    }


    @Test
    public void findByPhone_shouldReturnUserWithRespectivePhone() throws SQLException {
        String phone = "380938909084";
        Optional<User> user = userDao.findUserByPhone(phone);
        Assert.assertTrue("Dao should return existing user", user.isPresent());
    }

    @Test
    public void findByPhone_shouldReturnNull() throws SQLException {
        String phone = "ERROR";
        Optional<User> user = userDao.findUserByPhone(phone);
        Assert.assertFalse("Dao should return null if user doesn't exist", user.isPresent());
    }

    @Test
    public void update_shouldUpdateFirstName() throws SQLException {
        String login = "Alex5";
        Optional<User> user = userDao.findUserByLogin(login);
        String oldFirstName = user.get().getFirstname();
        String newFirstName = "TEST";
        user.get().setFirstname(newFirstName);
        userDao.update(user.get());
        Optional<User> updatedUser = userDao.findUserByLogin(login);
        Assert.assertNotEquals("FirstName should be updated", oldFirstName, updatedUser.get().getFirstname());
    }

    @Test(expected = SQLException.class)
    public void update_shouldThrowException() throws SQLException {
        String oldLogin = "Alex5";
        String newLogin = "Nick123";
        Optional<User> user = userDao.findUserByLogin(oldLogin);
        user.get().setLogin(newLogin);
        userDao.update(user.get());
    }


}