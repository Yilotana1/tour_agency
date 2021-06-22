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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
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

    @Override
    public void signUp(User user) throws UserAlreadyExistsException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.getConnection().setAutoCommit(false);
            userDao.getConnection().setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            User user1 = userDao.findUserByLogin(user.getLogin());
            User user2 = userDao.findUserByEmail(user.getEmail());
            User user3 = userDao.findUserByPhone(user.getPhone());

            if (user1 != null)
                throw new UserAlreadyExistsException("User with this login already exists in the system", user1);
            if (user2 != null)
                throw new UserAlreadyExistsException("User with this email already exists in the system", user2);
            if (user3 != null)
                throw new UserAlreadyExistsException("User with this phone already exists in the system", user3);

            userDao.create(user);

            userDao.getConnection().commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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
    public void updateUsers(List<User> users) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.getConnection().setAutoCommit(false);
            userDao.getConnection().setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            for (User user : users) {
                userDao.update(user);
            }

            userDao.getConnection().commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
    public User getById(int id){
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
    public List<User> getAllClients() throws ServiceException {
        return daoFactory.createUserDao().findAll()
                .stream().filter(user -> user.getRole().equals(Role.CLIENT))
                .collect(Collectors.toList());

    }

    @Override
    public List<User> getAllManagers() throws ServiceException {
        return daoFactory.createUserDao().findAll()
                .stream().filter(user -> user.getRole().equals(Role.MANAGER))
                .collect(Collectors.toList());

    }


    @Override
    public User getByLogin(String login) throws NoSuchElementException {
        return getAll().stream()
                .filter(client -> client.getLogin().equals(login))
                .findFirst().get();
    }

    @Override
    public List<User> getByFirstLastName(String firstName, String lastName) throws ServiceException {
        return getAll().stream()
                .filter(client -> client.getFirstname().equals(firstName) || client.getLastname().equals(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public User getByPhone(String phone) throws ServiceException {
        return getAll().stream()
                .filter(client -> client.getPhone().equals(phone))
                .findFirst().get();
    }

    @Override
    public List<User> getBlockedUsers() throws ServiceException {
        return getAll().stream()
                .filter(user -> user.getStatus().equals(UserStatus.BLOCKED))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getNonBlockedUsers() throws ServiceException {
        return getAll().stream()
                .filter(user -> user.getStatus().equals(UserStatus.NON_BLOCKED))
                .collect(Collectors.toList());
    }


    @Override
    public void block(int id) throws ServiceException {
        User client = getById(id);
        client.setStatus(UserStatus.BLOCKED);
        daoFactory.createUserDao().update(client);
    }

    @Override
    public void block(String login) throws ServiceException {
        User client = getByLogin(login);
        client.setStatus(UserStatus.BLOCKED);
        daoFactory.createUserDao().update(client);
    }

    @Override
    public void unBlock(int id) throws ServiceException {
        User client = getById(id);
        client.setStatus(UserStatus.NON_BLOCKED);
        daoFactory.createUserDao().update(client);
    }

    @Override
    public void unBlock(String login) throws ServiceException {
        User client = getByLogin(login);
        client.setStatus(UserStatus.NON_BLOCKED);
        daoFactory.createUserDao().update(client);
    }

    @Override
    public int add(User user) {
        return daoFactory.createUserDao().create(user);
    }

    @Override
    public void remove(int id) {}

}
