package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findReservationEntitiesByStartDateAfterAndEndDateBefore(LocalDate startDate, LocalDate endDate);

}
