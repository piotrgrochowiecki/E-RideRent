package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.ReservationDto;
import com.piotgrochowiecki.eriderent.model.Reservation;
import com.piotgrochowiecki.eriderent.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service("ReservationService")
@Slf4j
public class ReservationService implements ReservationServiceInterface {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> findAllReservationsOverlappingWithDates(LocalDate newReservationStartDate, LocalDate newReservationEndDate) {
        List<Reservation> allReservationList = reservationRepository.findAll();
        List<Reservation> reservationListWithDatesOverlappingWithNewReservationDate = new ArrayList<>();
        for (int i = 0; i < allReservationList.size(); i++) {
            if (allReservationList.get(i).getStartDate().compareTo(newReservationEndDate) <= 0 &&
                    allReservationList.get(i).getEndDate().compareTo(newReservationStartDate) >= 0) {
                reservationListWithDatesOverlappingWithNewReservationDate.add(allReservationList.get(i));
                //after https://stackoverflow.com/questions/325933/determine-whether-two-date-ranges-overlap
            }
        }
        return reservationListWithDatesOverlappingWithNewReservationDate;
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public void add(ReservationDto reservationDto) {
        reservationRepository.save(
                Reservation.builder()
                        .startDate(reservationDto.getStartDate())
                        .endDate(reservationDto.getEndDate())
                        .car(reservationDto.getCar())
                        .user(reservationDto.getUser())
                        .build());
    }
}
