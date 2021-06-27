package com.example.touragency.model.service.impl;

import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.dao.UserDao;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.entity.enums.UserStatus;
import com.example.touragency.exceptions.*;
import com.example.touragency.model.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public User signIn(String login, String password) throws InvalidCredentialsException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            User user = userDao.findUserByLogin(login);

            if (user == null || !user.getPassword().equals(password)) {
                throw new InvalidCredentialsException("Login or password not found", login, password);
            }
            return user;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public void update(int id, String firstName, String lastName, String phone, String email, UserStatus status,
                       String login, String password, Role role) {

        try (UserDao userDao = daoFactory.createUserDao()) {
            User user = User.createUser(id, firstName, lastName, phone, email, status, login, password, role);
            userDao.update(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(String currentLogin, String firstName, String lastName,
                       String phone, String email, UserStatus status,
                       String login, String password, Role role) throws ServiceException {
        try (UserDao userDao = daoFactory.createUserDao()) {

            userDao.getConnection().setAutoCommit(false);
            userDao.getConnection().setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            checkDataToUpdate(currentLogin, login, email, phone, userDao);

            userDao.update(currentLogin, firstName, lastName, phone, email, status, login, password, role);

            userDao.getConnection().commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void checkDataToUpdate(String currentLogin, String login, String email, String phone, UserDao userDao) throws ServiceException{
        User user1 = userDao.findUserByLogin(login);
        User user2 = userDao.findUserByEmail(email);
        User user3 = userDao.findUserByPhone(phone);
        User userToChange = userDao.findUserByLogin(currentLogin);

        if (user1 != null && !userToChange.getLogin().equals(login))
            throw new ServiceException("Login already exists");
        if (user2 != null && !userToChange.getEmail().equals(email))
            throw new ServiceException("Email already exists");
        if (user3 != null && !userToChange.getPhone().equals(phone))
            throw new ServiceException("Phone number already exists");
    };


    @Override
    public void signUp(User user) throws ServiceException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.getConnection().setAutoCommit(false);
            userDao.getConnection().setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            checkDataToCreate(user, userDao);
            userDao.create(user);

            userDao.getConnection().commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void checkDataToCreate(User user, UserDao userDao) throws ServiceException{
        User user1 = userDao.findUserByLogin(user.getLogin());
        User user2 = userDao.findUserByEmail(user.getEmail());
        User user3 = userDao.findUserByPhone(user.getPhone());

        if (user1 != null)
            throw new ServiceException("Login already exists");
        if (user2 != null)
            throw new ServiceException("Email already exists");
        if (user3 != null)
            throw new ServiceException("Phone number already exists");
    };

    @Override
    public int getCount() {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.getCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }


    @Override
    public List<User> getAll() {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getPage(int pageId, int pageSize) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findByLimit(pageId * pageSize - pageSize + 1, pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getPageClientsFirst(int pageId, int pageSize) {
        try (UserDao userDao = daoFactory.createUserDao()) {

            return userDao.findByLimitClientsFirst(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getPageManagersFirst(int pageId, int pageSize) {
        try (UserDao userDao = daoFactory.createUserDao()) {

            return userDao.findByLimitManagersFirst(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getPageNonBlockedFirst(int pageId, int pageSize) {
        try (UserDao userDao = daoFactory.createUserDao()) {

            return userDao.findByLimitNonBlockedFirst(pageId * pageSize - pageSize + 1, pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public List<User> getPageBlockedFirst(int pageId, int pageSize) {
        try (UserDao userDao = daoFactory.createUserDao()) {

            return userDao.findByLimitBlockedFirst(pageId * pageSize - pageSize + 1, pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public void update(User user) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.update(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User getById(int id) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public User getAdmin() throws ServiceException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAll()
                    .stream()
                    .filter(user -> user.getRole()
                            .equals(Role.ADMIN)).findFirst().get();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new ServiceException("Cannot read admin data from db");
        }
    }


    @Override
    public User getByLogin(String login) throws NoSuchElementException {
        return getAll().stream()
                .filter(client -> client.getLogin().equals(login))
                .findFirst().get();
    }

    @Override
    public int add(User user) {
        return daoFactory.createUserDao().create(user);
    }

}
