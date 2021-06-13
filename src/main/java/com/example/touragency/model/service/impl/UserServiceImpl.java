package com.example.touragency.model.service.impl;

import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.dao.UserDao;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.entity.enums.UserStatus;
import com.example.touragency.model.exceptions.DaoException;
import com.example.touragency.model.exceptions.ServiceException;
import com.example.touragency.model.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();




    @Override
    public List<User> getAllUsers() throws ServiceException{
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserById(int id) throws ServiceException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public User getAdmin() throws ServiceException{
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
        try {
            return daoFactory.createUserDao().findAll()
                    .stream().filter(user -> user.getRole().equals(Role.CLIENT))
                    .collect(Collectors.toList());

        } catch (DaoException throwables) {
            throwables.printStackTrace();
            throw new ServiceException("Cannot read all clients data from db");
        }
    }

    @Override
    public List<User> getAllManagers() throws ServiceException {
        try {
            return daoFactory.createUserDao().findAll()
                    .stream().filter(user -> user.getRole().equals(Role.MANAGER))
                    .collect(Collectors.toList());

        } catch (DaoException throwables) {
            throwables.printStackTrace();
            throw new ServiceException("Cannot read all managers data from db");
        }
    }


    @Override
    public User getUserByLogin(String login) throws ServiceException {
        return getAllUsers().stream()
                .filter(client -> client.getLogin().equals(login))
                .findFirst().get();
    }

    @Override
    public List<User> getUserByFirstLastName(String firstName, String lastName) throws ServiceException {
        return getAllUsers().stream()
                .filter(client -> client.getFirstname().equals(firstName) || client.getLastname().equals(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public User getUserByPhone(String phone) throws ServiceException {
        return getAllUsers().stream()
                .filter(client -> client.getPhone().equals(phone))
                .findFirst().get();
    }

    @Override
    public List<User> getBlockedUsers() throws ServiceException {
        return getAllUsers().stream()
                .filter(user -> user.getStatus().equals(UserStatus.BLOCKED))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getNonBlockedUsers() throws ServiceException {
        return getAllUsers().stream()
                .filter(user -> user.getStatus().equals(UserStatus.NON_BLOCKED))
                .collect(Collectors.toList());
    }




    @Override
    public void blockUser(int id) throws ServiceException{
        User client = getUserById(id);
        client.setStatus(UserStatus.BLOCKED);
        try {
            daoFactory.createUserDao().update(client);
        } catch (DaoException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void blockUser(String login) throws ServiceException {
        User client = getUserByLogin(login);
        client.setStatus(UserStatus.BLOCKED);
        try {
            daoFactory.createUserDao().update(client);
        } catch (DaoException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void unBlockUser(int id) throws ServiceException {
        User client = getUserById(id);
        client.setStatus(UserStatus.NON_BLOCKED);
        try {
            daoFactory.createUserDao().update(client);
        } catch (DaoException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void unBlockUser(String login) throws ServiceException {
        User client = getUserByLogin(login);
        client.setStatus(UserStatus.NON_BLOCKED);
        try {
            daoFactory.createUserDao().update(client);
        } catch (DaoException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addUser(User user){
        try {
            daoFactory.createUserDao().create(user);
        } catch (DaoException throwables) {
            throwables.printStackTrace();
        }
    }


}
