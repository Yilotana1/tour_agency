package com.example.touragency.model.service;

import com.example.touragency.model.entity.User;
import com.example.touragency.exceptions.*;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.entity.enums.UserStatus;


import java.util.List;
import java.util.Optional;

public interface UserService extends Service<User> {

    Optional<User> signIn(String login, String password) throws ServiceException;

    void update(int id, String firstName, String lastName, String phone, String email, UserStatus status,
                String login, String password, Role role) throws ServiceException;

    void update(String currentLogin, String firstName, String lastName, String phone, String email, UserStatus status, String login,
                String password, Role role) throws ServiceException;

    void signUp(User user) throws ServiceException;

    List<User> getPageClientsFirst(int pageId, int pageSize) throws ServiceException;

    List<User> getPageManagersFirst(int pageId, int pageSize) throws ServiceException;

    List<User> getPageBlockedFirst(int pageId, int pageSize) throws ServiceException;

    List<User> getPageNonBlockedFirst(int pageId, int pageSize) throws ServiceException;

    Optional<User> getByLogin(String login) throws ServiceException;

}
