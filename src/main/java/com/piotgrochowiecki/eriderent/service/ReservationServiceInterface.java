package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.request.ReservationCreateRequestDto;
import com.piotgrochowiecki.eriderent.dto.response.ReservationResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ReservationServiceInterface {

    List<ReservationResponseDto> findAllReservationsOverlappingWithDates(LocalDate newReservationStartDate, LocalDate newReservationEndDate);

    List<ReservationResponseDto> getAll();

    void add(ReservationCreateRequestDto reservationCreateRequestDto);
}
