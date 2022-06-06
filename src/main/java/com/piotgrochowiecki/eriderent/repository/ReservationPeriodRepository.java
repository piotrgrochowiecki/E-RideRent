package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.ReservationPeriodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationPeriodRepository extends JpaRepository<ReservationPeriodEntity, Long> {
}
