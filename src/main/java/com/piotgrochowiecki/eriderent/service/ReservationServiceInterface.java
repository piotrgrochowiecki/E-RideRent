package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.ReservationEntity;

import java.time.LocalDate;
import java.util.List;

public interface ReservationServiceInterface {

    List<ReservationEntity> findAllReservationsOverlappingWithDates(LocalDate newReservationStartDate, LocalDate newReservationEndDate);

    List<ReservationEntity> findAll();

    void add(ReservationEntity reservation);
}
