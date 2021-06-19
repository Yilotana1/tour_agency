package com.example.touragency.model.dao;

import com.example.touragency.model.entity.User;

import java.sql.SQLException;

public interface UserDao extends GenericDao<User>{

    User findUserByLogin(String login);

    User findUserByEmail(String email);

    User findUserByPhone(String phone);
}
