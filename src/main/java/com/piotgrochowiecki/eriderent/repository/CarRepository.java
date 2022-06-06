package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
}
