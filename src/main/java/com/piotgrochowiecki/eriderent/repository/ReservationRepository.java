package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    List<ReservationEntity> findReservationEntitiesByStartDateAfterAndEndDateBefore(LocalDate startDate, LocalDate endDate);

}
