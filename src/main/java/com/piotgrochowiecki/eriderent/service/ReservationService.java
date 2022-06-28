package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.Reservation;
import com.piotgrochowiecki.eriderent.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService implements ReservationServiceInterface {


    private final ReservationRepository reservationRepository;

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
    public void add(Reservation reservation) {
        reservationRepository.save(
                Reservation.builder()
                        .startDate(reservation.getStartDate())
                        .endDate(reservation.getEndDate())
                        .car(reservation.getCar())
                        .user(reservation.getUser())
                        .build());
    }
}
