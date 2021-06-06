package com.example.touragency.model.dao;

import com.example.touragency.model.exceptions.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    void create(T entity) throws DaoException;

    T findById(int id) throws DaoException;

    List<T> findAll() throws DaoException;

    void update(T entity) throws DaoException;

    void delete(int id) throws DaoException;

    void close() throws SQLException;


}
