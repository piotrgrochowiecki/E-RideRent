package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.CarEntity;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<CarEntity> findAll();
    void addCar(CarEntity car);
    Optional<CarEntity> findById(Long id);
    void deleteById(Long id);
    void update(CarEntity car);
}