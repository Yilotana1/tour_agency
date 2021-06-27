package com.example.touragency.model.service;

import com.example.touragency.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

public interface Service<T> {

    Optional<T> getById(int id);

    List<T> getAll();

//    void update() throws ServiceException;

    void update(T entity) throws ServiceException;

    List<T> getPage(int pageId, int pageSize);

    int add(T entity);

    int getCount();

}
