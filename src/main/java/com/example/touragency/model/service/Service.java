package com.example.touragency.model.service;

import com.example.touragency.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

public interface Service<T> {

    Optional<T> getById(int id) throws ServiceException;

    List<T> getAll() throws ServiceException;

//    void update() throws ServiceException;

    void update(T entity) throws ServiceException;

    List<T> getPage(int pageId, int pageSize) throws ServiceException;

    int add(T entity) throws ServiceException;

    int getCount() throws ServiceException;

}
