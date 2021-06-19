package com.example.touragency.model.service;

import com.example.touragency.model.entity.User;
import com.example.touragency.exceptions.*;

import java.util.List;

public interface UserService {

    User signIn(String login, String password) throws InvalidCredentialsException;

//    boolean userIsPresent(String login, String email);

    void signUp(User user) throws UserAlreadyExistsException;

    List<User> getAllUsers();

    List<User> getNonBlockedUsers() throws ServiceException;

    List<User> getBlockedUsers() throws ServiceException;

    User getAdmin() throws ServiceException;

    List<User> getAllClients() throws ServiceException;

    List<User> getAllManagers() throws ServiceException;

    User getUserById(int id) throws ServiceException;

    User getUserByLogin(String login) throws ServiceException;

    User getUserByPhone(String phone) throws ServiceException;

    List<User> getUserByFirstLastName(String firstName, String lastname) throws ServiceException;

    void blockUser(int id) throws ServiceException;

    void blockUser(String login) throws ServiceException;

    void unBlockUser(int id) throws ServiceException;

    void unBlockUser(String login) throws ServiceException;

    void addUser(User user);

}
