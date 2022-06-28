package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<Car, Long> {

//    List<CarEntity> findByReservationList(List<ReservationEntity> reservationList);

}
