package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.ReservationEntity;
import com.piotgrochowiecki.eriderent.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JpaReservationService implements ReservationService {


    private final ReservationRepository reservationRepository;

    @Override
    public List<ReservationEntity> findAllReservationsOverlappingWithDates(LocalDate newReservationStartDate, LocalDate newReservationEndDate) {
        List<ReservationEntity> allReservationList = reservationRepository.findAll();
        List<ReservationEntity> reservationListWithDatesOverlappingWithNewReservationDate = new ArrayList<>();
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
    public List<ReservationEntity> findAll() {
        return reservationRepository.findAll();
    }

}
