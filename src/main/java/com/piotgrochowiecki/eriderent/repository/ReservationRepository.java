package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    List<ReservationEntity> findReservationEntitiesByStartDateAfterAndEndDateBefore(LocalDate startDate, LocalDate endDate);

}
