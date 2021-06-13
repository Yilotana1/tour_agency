package com.example.touragency.model.dao;

import com.example.touragency.model.entity.User;
import com.example.touragency.model.exceptions.DaoException;

public interface UserDao extends GenericDao<User>{

    User findUserByLogin(String login) throws DaoException;
}
