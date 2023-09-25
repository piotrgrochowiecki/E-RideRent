package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


/**
 * Car repository
 */
public interface CarRepository extends JpaRepository<Car, Long> {

    /**
     * Finds car entity based on model and brand
     *
     * @param brand brand of requested car
     * @param model model of requested car
     * @return Optional of car entity
     */
    @Query("SELECT c FROM Car c WHERE c.brand = ?1 AND c.model = ?2")
    Optional<Car> findByFullCarName(String brand, String model);

}
