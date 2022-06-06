package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
}
