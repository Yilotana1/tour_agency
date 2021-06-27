package com.example.touragency.model.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {

    Connection getConnection() throws SQLException;

    int getCount();

    int create(T entity);

    Optional<T> findById(int id);

    List<T> findAll();

    List<T> findByLimit(int start, int count);

    void update(T entity);

    void delete(int id);

    void close() throws SQLException;


}
