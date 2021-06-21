package com.example.touragency.model.dao;

import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.entity.enums.UserStatus;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends GenericDao<User>{

    User findUserByLogin(String login);

    User findUserByEmail(String email);

    User findUserByPhone(String phone);

    List<User> findByLimitManagersFirst(int start, int count);

    List<User> findByLimitClientsFirst(int start, int count);

    List<User> findByLimitNonBlockedFirst(int start, int count);

    List<User> findByLimitBlockedFirst(int start, int count);
}
