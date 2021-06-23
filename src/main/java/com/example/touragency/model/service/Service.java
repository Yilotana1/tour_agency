package com.example.touragency.model.service;

import com.example.touragency.exceptions.ServiceException;

import java.util.List;

public interface Service<T> {

    T getById(int id);

    List<T> getAll();

    void update() throws ServiceException;

    void update(T entity) throws ServiceException;

    List<T> getPage(int pageId, int pageSize);

    int add(T entity);

    void remove(int id);

    int getCount();

}
