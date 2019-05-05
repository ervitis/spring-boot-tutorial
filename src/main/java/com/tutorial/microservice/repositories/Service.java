package com.tutorial.microservice.repositories;

import java.util.List;

public interface Service<T> {
    List<T> findAll();

    T save(T data);

    T findOne(int id);

    boolean delete(int id);
}
