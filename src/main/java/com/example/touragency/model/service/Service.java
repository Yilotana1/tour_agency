package com.example.touragency.model.service;

import java.util.List;

public interface Service<T> {

    T getById(int id);

    List<T> getAll();

    void update(T entity);

    List<T> getPage(int pageId, int pageSize);

    int add(T entity);

    void remove(int id);

    int getCount();

}
