package com.example.touragency.model.service;

import com.example.touragency.model.entity.User;
import com.example.touragency.exceptions.*;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.entity.enums.TourCategory;
import com.example.touragency.model.entity.enums.TourStatus;
import com.example.touragency.model.entity.enums.UserStatus;


import javax.naming.OperationNotSupportedException;
import java.util.Calendar;
import java.util.List;

public interface UserService extends Service<User> {

    User signIn(String login, String password) throws InvalidCredentialsException;

    void update(int id, String firstName, String lastName, String phone, String email, UserStatus status,
                String login, String password, Role role);

    void update(String currentLogin, String firstName, String lastName, String phone, String email, UserStatus status, String login,
                String password, Role role) throws ServiceException;

    void signUp(User user) throws ServiceException;

    List<User> getPageClientsFirst(int pageId, int pageSize);

    List<User> getPageManagersFirst(int pageId, int pageSize);

    List<User> getPageBlockedFirst(int pageId, int pageSize);

    List<User> getPageNonBlockedFirst(int pageId, int pageSize);

    User getAdmin() throws ServiceException;

    User getByLogin(String login);

}
