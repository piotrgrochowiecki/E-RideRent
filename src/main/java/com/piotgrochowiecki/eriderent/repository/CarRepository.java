package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.CarEntity;
import com.piotgrochowiecki.eriderent.model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    List<CarEntity> findByReservationList(List<ReservationEntity> reservationList);

}
