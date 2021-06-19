package com.example.touragency.model.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {

    Connection getConnection() throws SQLException;

    int create(T entity);

    T findById(int id);

    List<T> findAll();

    void update(T entity);

    void delete(int id);

    void close() throws SQLException;


}
