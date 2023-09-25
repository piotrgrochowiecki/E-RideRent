package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.request.ReservationCreateRequestDto;
import com.piotgrochowiecki.eriderent.dto.response.ReservationResponseDto;
import com.piotgrochowiecki.eriderent.model.Reservation;
import com.piotgrochowiecki.eriderent.repository.ReservationRepository;
import com.piotgrochowiecki.eriderent.service.functionalInterfaces.TriPredicate;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.piotgrochowiecki.eriderent.dto.request.ReservationCreateRequestDto.map;

/**
 * Reservation service.
 */
@Service("ReservationService")
@CommonsLog
public class ReservationService implements ReservationServiceInterface {

    private final ReservationRepository reservationRepository;


    /**
     * Constructor
     *
     * @param reservationRepository
     */
    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * Returns list of all existing reservations which have start and end dates overlapping with dates of new reservation (including the same days). <br>
     * Example: Existing reservation has end date of October 15, new reservation has start date of October 15 and end date of October 27.
     * Existing reservation will be added to the returned list.
     *
     * @param newReservationStartDate start date of new reservation
     * @param newReservationEndDate end date of new reservation
     * @return list of saved reservations (as ReservationResponseDto objects) which overlap with new reservation
     */
    @Override
    public List<ReservationResponseDto> findAllReservationsOverlappingWithDates(LocalDate newReservationStartDate, LocalDate newReservationEndDate) {
        List<ReservationResponseDto> allReservationList = getAll();
        return allReservationList.stream()
                .filter(r -> isReservationOverlappingExistingReservations.test(newReservationStartDate, newReservationEndDate, r))
                .collect(Collectors.toList());
    }

    /**
     * Returns list of all saved reservations (as <code>ReservationResponseDto</code> objects)
     *
     * @return list of all saved reservations (as <code>ReservationResponseDto</code> objects)
     */
    @Override
    public List<ReservationResponseDto> getAll() {
        return reservationRepository.findAll().stream()
                .map(ReservationResponseDto::map)
                .collect(Collectors.toList());
    }

    /**
     * Takes <code>ReservationCreateRequestDto</code> type of object, maps it to <code>Reservation</code> entity object and passes it into repository.
     *
     * @param reservationCreateRequestDto DTO object used to create new reservation
     */
    @Override
    public void add(ReservationCreateRequestDto reservationCreateRequestDto) {
        Reservation reservation = map(reservationCreateRequestDto);
        reservationRepository.save(reservation);
    }

    private final TriPredicate<LocalDate, LocalDate, ReservationResponseDto>
            isReservationOverlappingExistingReservations = (newReservationStartDate, newReservationEndDate, existingReservation) -> ((existingReservation.getStartDate().isBefore(newReservationEndDate) || existingReservation.getStartDate().isEqual(newReservationEndDate))
                                                                                                                                     && (existingReservation.getEndDate().isAfter(newReservationStartDate) || existingReservation.getEndDate().isEqual(newReservationStartDate)));

}
