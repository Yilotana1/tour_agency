package com.example.touragency.model.service;

import com.example.touragency.model.entity.User;

import java.util.List;

public interface ClientService {

    List<User> getAllClients();

    List<User> getNonBlockedClients();

    List<User> getBlockedClients();

    List<User> getClientsWithOrders();

    List<User> getClientsWithoutOrders();

    User getClient(int id);

    User getClient(String login);

    User getClientByPhone(String phone);

    List<User> getClient(String firstName, String lastname);

    void blockClient(int id);

    void blockClient(String login);

    void unBlockClient(int id);

    void unBlockClient(String login);


}
