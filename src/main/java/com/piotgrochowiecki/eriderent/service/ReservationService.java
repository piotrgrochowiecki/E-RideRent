package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.request.ReservationCreateRequestDto;
import com.piotgrochowiecki.eriderent.dto.response.ReservationResponseDto;
import com.piotgrochowiecki.eriderent.model.Reservation;
import com.piotgrochowiecki.eriderent.repository.ReservationRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.piotgrochowiecki.eriderent.dto.request.ReservationCreateRequestDto.map;

@Service("ReservationService")
@CommonsLog
public class ReservationService implements ReservationServiceInterface {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<ReservationResponseDto> findAllReservationsOverlappingWithDates(LocalDate newReservationStartDate, LocalDate newReservationEndDate) {
        List<ReservationResponseDto> allReservationList = getAll();
        List<ReservationResponseDto> reservationListWithDatesOverlappingWithNewReservationDate = new ArrayList<>();
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
    public List<ReservationResponseDto> getAll() {
        return reservationRepository.findAll().stream()
                .map(ReservationResponseDto::map)
                .collect(Collectors.toList());
    }

    @Override
    public void add(ReservationCreateRequestDto reservationCreateRequestDto) {
        Reservation reservation = map(reservationCreateRequestDto);
        reservationRepository.save(reservation);
    }
}
