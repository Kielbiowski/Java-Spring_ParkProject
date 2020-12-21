package com.kielbiowski.parkproject.service;

public interface ServiceInterface<T> {
    T findById(Integer id);

    T create(T t);

    T update(T t);

    void delete(Integer id);
}
