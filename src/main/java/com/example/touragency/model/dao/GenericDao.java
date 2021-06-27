package com.example.touragency.model.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {

    Connection getConnection() throws SQLException;

    int getCount() throws SQLException;

    int create(T entity) throws SQLException;

    Optional<T> findById(int id) throws SQLException;

    List<T> findAll() throws SQLException;

    List<T> findByLimit(int start, int count) throws SQLException;

    void update(T entity) throws SQLException;

    void delete(int id) throws SQLException;

    void close() throws SQLException;


}
