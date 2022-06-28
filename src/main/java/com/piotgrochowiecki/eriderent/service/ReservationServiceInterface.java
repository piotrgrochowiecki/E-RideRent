package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationServiceInterface {

    List<Reservation> findAllReservationsOverlappingWithDates(LocalDate newReservationStartDate, LocalDate newReservationEndDate);

    List<Reservation> findAll();

    void add(Reservation reservation);
}
