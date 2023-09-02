package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.response.ReservationResponseDto;
import com.piotgrochowiecki.eriderent.model.Car;
import com.piotgrochowiecki.eriderent.model.Reservation;
import com.piotgrochowiecki.eriderent.repository.ReservationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

    Reservation reservation1 = Reservation.builder()
            .id(1L)
            .startDate(LocalDate.of(2023, 6, 10))
            .endDate(LocalDate.of(2023, 7, 20))
            .car(Car.builder()
                         .id(1L)
                         .build())
            .build();
    Reservation reservation2 = Reservation.builder()
            .id(2L)
            .startDate(LocalDate.of(2023, 6, 21))
            .endDate(LocalDate.of(2023, 6, 30))
            .car(Car.builder()
                         .id(2L)
                         .build())
            .build();
    Reservation reservation3 = Reservation.builder()
            .id(3L)
            .startDate(LocalDate.of(2023, 7, 1))
            .endDate(LocalDate.of(2023, 7, 10))
            .car(Car.builder()
                         .id(3L)
                         .build())
            .build();
    Reservation reservation4 = Reservation.builder()
            .id(4L)
            .startDate(LocalDate.of(2023, 7, 11))
            .endDate(LocalDate.of(2023, 7, 20))
            .car(Car.builder()
                         .id(4L)
                         .build())
            .build();
    Reservation reservation5 = Reservation.builder()
            .id(5L)
            .startDate(LocalDate.of(2023, 7, 21))
            .endDate(LocalDate.of(2023, 7, 30))
            .car(Car.builder()
                         .id(5L)
                         .build())
            .build();
    Reservation reservation6 = Reservation.builder()
            .id(6L)
            .startDate(LocalDate.of(2023, 6, 15))
            .endDate(LocalDate.of(2023, 6, 25))
            .car(Car.builder()
                         .id(6L)
                         .build())
            .build();
    Reservation reservation7 = Reservation.builder()
            .id(7L)
            .startDate(LocalDate.of(2023, 6, 26))
            .endDate(LocalDate.of(2023, 7, 5))
            .car(Car.builder()
                         .id(7L)
                         .build())
            .build();
    Reservation reservation8 = Reservation.builder()
            .id(8L)
            .startDate(LocalDate.of(2023, 7, 6))
            .endDate(LocalDate.of(2023, 7, 15))
            .car(Car.builder()
                         .id(8L)
                         .build())
            .build();
    Reservation reservation9 = Reservation.builder()
            .id(9L)
            .startDate(LocalDate.of(2023, 7, 16))
            .endDate(LocalDate.of(2023, 7, 25))
            .car(Car.builder()
                         .id(9L)
                         .build())
            .build();
    Reservation reservation10 = Reservation.builder()
            .id(9L)
            .startDate(LocalDate.of(2023, 8, 4))
            .endDate(LocalDate.of(2023, 8, 26))
            .car(Car.builder()
                         .id(10L)
                         .build())
            .build();

    ReservationResponseDto reservationResponseDto1 = ReservationResponseDto.map(reservation1);
    ReservationResponseDto reservationResponseDto2 = ReservationResponseDto.map(reservation2);
    ReservationResponseDto reservationResponseDto3 = ReservationResponseDto.map(reservation3);
    ReservationResponseDto reservationResponseDto4 = ReservationResponseDto.map(reservation4);
    ReservationResponseDto reservationResponseDto5 = ReservationResponseDto.map(reservation5);
    ReservationResponseDto reservationResponseDto6 = ReservationResponseDto.map(reservation6);
    ReservationResponseDto reservationResponseDto7 = ReservationResponseDto.map(reservation7);
    ReservationResponseDto reservationResponseDto8 = ReservationResponseDto.map(reservation8);
    ReservationResponseDto reservationResponseDto9 = ReservationResponseDto.map(reservation9);
    ReservationResponseDto reservationResponseDto10 = ReservationResponseDto.map(reservation10);


    @BeforeEach
    public void setup() {
        List<Reservation> allReservations = Arrays.asList(reservation1, reservation2, reservation3, reservation4, reservation5, reservation6, reservation7, reservation8, reservation9, reservation10);

        Mockito.when(reservationRepository.findAll())
                .thenReturn(allReservations);
    }


    @Test
    @DisplayName("Given some period of new reservation, when findAllReservationsOverlappingWithDates method is called, it should return list of 6 existing reservations with overlapping periods")
    public void findAllReservationsOverlappingWithDatesTest() {
        //given
        LocalDate newReservationStartDate = LocalDate.of(2023, 6, 10);
        LocalDate newReservationEndDate = LocalDate.of(2023, 7, 6);

        //when
        List<ReservationResponseDto> resultList =
                reservationService.findAllReservationsOverlappingWithDates(newReservationStartDate, newReservationEndDate);

        //then
        Assertions.assertTrue(resultList.contains(reservationResponseDto1));
        Assertions.assertTrue(resultList.contains(reservationResponseDto2));
        Assertions.assertTrue(resultList.contains(reservationResponseDto3));
        Assertions.assertTrue(resultList.contains(reservationResponseDto6));
        Assertions.assertTrue(resultList.contains(reservationResponseDto7));
        Assertions.assertTrue(resultList.contains(reservationResponseDto8));
        Assertions.assertEquals(6, resultList.size());
    }

    @Test
    @DisplayName("Given period of new reservation exactly overlapping with one preexisting reservation, when findAllReservationsOverlappingWithDates method is called, it should return list containing only this particular reservation")
    public void findAllReservationsOverlappingWithDatesTest2() {
        //given
        LocalDate newReservationStartDate = LocalDate.of(2023, 8, 4);
        LocalDate newReservationEndDate = LocalDate.of(2023, 8, 26);

        //when
        List<ReservationResponseDto> resultList =
                reservationService.findAllReservationsOverlappingWithDates(newReservationStartDate, newReservationEndDate);

        //then
        Assertions.assertTrue(resultList.contains(reservationResponseDto10));
        Assertions.assertEquals(1, resultList.size());
    }

    @Test
    @DisplayName("Given period of new reservation exactly not overlapping with any preexisting reservation, when findAllReservationsOverlappingWithDates method is called, it should return empty list")
    public void findAllReservationsOverlappingWithDatesTest3() {
        //given
        LocalDate newReservationStartDate = LocalDate.of(2023, 9, 10);
        LocalDate newReservationEndDate = LocalDate.of(2023, 9, 15);

        //when
        List<ReservationResponseDto> resultList =
                reservationService.findAllReservationsOverlappingWithDates(newReservationStartDate, newReservationEndDate);

        //then
        Assertions.assertTrue(resultList.isEmpty());
    }

    @Test
    @DisplayName("Given end date of new reservation being the same as start date of preexisting reservation, when findAllReservationsOverlappingWithDates method is called, it should return list with that particular reservation")
    public void findAllReservationsOverlappingWithDatesTest4() {
        LocalDate newReservationStartDate = LocalDate.of(2023, 8, 1);
        LocalDate newReservationEndDate = LocalDate.of(2023, 8, 4);

        //when
        List<ReservationResponseDto> resultList =
                reservationService.findAllReservationsOverlappingWithDates(newReservationStartDate, newReservationEndDate);

        //then
        Assertions.assertTrue(resultList.contains(reservationResponseDto10));
        Assertions.assertEquals(1, resultList.size());
    }

    @Test
    @DisplayName("Given start date of new reservation being the same as end date of preexisting reservation, when findAllReservationsOverlappingWithDates method is called, it should return list with that particular reservation")
    public void findAllReservationsOverlappingWithDatesTest5() {
        LocalDate newReservationStartDate = LocalDate.of(2023, 8, 26);
        LocalDate newReservationEndDate = LocalDate.of(2023, 8, 30);

        //when
        List<ReservationResponseDto> resultList =
                reservationService.findAllReservationsOverlappingWithDates(newReservationStartDate, newReservationEndDate);

        //then
        Assertions.assertTrue(resultList.contains(reservationResponseDto10));
        Assertions.assertEquals(1, resultList.size());
    }

}
