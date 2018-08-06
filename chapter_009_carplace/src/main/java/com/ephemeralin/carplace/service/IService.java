package com.ephemeralin.carplace.service;

import java.util.List;

public interface IService<T> {
    int create(T entity);
    T findById(int id);
    List<T> findAll();
    T update(T entity);
    boolean delete(int id);
}
