package com.example.touragency.model.service.impl;

import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.dao.OrderClientBeanDao;
import com.example.touragency.model.dao.UserDao;
import com.example.touragency.model.dao.beans.OrderClientBean;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.UserStatus;
import com.example.touragency.model.exceptions.DaoException;
import com.example.touragency.model.service.ClientService;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ClientServiceImpl implements ClientService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public List<User> getAllClients() {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public User getClient(int id) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public User getClient(String login) {
        return getAllClients().stream()
                .filter(client -> client.getLogin().equals(login))
                .findFirst().get();
    }

    @Override
    public List<User> getClient(String firstName, String lastName) {
        return getAllClients().stream()
                .filter(client -> client.getFirstname().equals(firstName) || client.getLastname().equals(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public User getClientByPhone(String phone) {
        return getAllClients().stream()
                .filter(client -> client.getPhone().equals(phone))
                .findFirst().get();
    }

    @Override
    public List<User> getBlockedClients() {
        return getAllClients().stream()
                .filter(user -> user.getStatus().equals(UserStatus.BLOCKED))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getNonBlockedClients() {
        return getAllClients().stream()
                .filter(user -> user.getStatus().equals(UserStatus.NON_BLOCKED))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getClientsWithOrders() {
        try (OrderClientBeanDao orderClientBeanDao = daoFactory.createOrderClientBeanDao()){
            return orderClientBeanDao.findAll()
                    .stream()
                    .map(OrderClientBean::getClient)
                    .distinct()
                    .sorted(Comparator.comparing(User::getId))
                    .collect(Collectors.toList());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public List<User> getClientsWithoutOrders() {
        List<User> clients = getAllClients();
        List<User> clientsWithOrders = getClientsWithOrders();
        clients.removeAll(clientsWithOrders);
        return clients;
    }


    @Override
    public void blockClient(int id) {
        User client = getClient(id);
        client.setStatus(UserStatus.BLOCKED);
        try {
            daoFactory.createUserDao().update(client);
        } catch (DaoException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void blockClient(String login) {
        User client = getClient(login);
        client.setStatus(UserStatus.BLOCKED);
        try {
            daoFactory.createUserDao().update(client);
        } catch (DaoException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void unBlockClient(int id) {
        User client = getClient(id);
        client.setStatus(UserStatus.NON_BLOCKED);
        try {
            daoFactory.createUserDao().update(client);
        } catch (DaoException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void unBlockClient(String login) {
        User client = getClient(login);
        client.setStatus(UserStatus.NON_BLOCKED);
        try {
            daoFactory.createUserDao().update(client);
        } catch (DaoException throwables) {
            throwables.printStackTrace();
        }
    }
}
