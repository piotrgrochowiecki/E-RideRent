package com.piotgrochowiecki.eriderent.dto.response;

import com.piotgrochowiecki.eriderent.dto.mapper.BaseMapper;
import com.piotgrochowiecki.eriderent.model.Reservation;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationResponseDto extends BaseMapper {

    Long id;

    LocalDate startDate;

    LocalDate endDate;

    CarResponseDto carResponseDto;

    UserResponseDto userResponseDto;

    public static ReservationResponseDto map(Reservation reservation) {
        ReservationResponseDto reservationResponseDto = map(reservation, ReservationResponseDto.class);

        CarResponseDto carResponseDto1 = CarResponseDto.builder()
                .id(reservation.getCar().getId())
                .brand(reservation.getCar().getBrand())
                .model(reservation.getCar().getModel())
                .accelerationSec(reservation.getCar().getAccelerationSec())
                .fastChargeKmh(reservation.getCar().getFastChargeKmh())
                .powerTrain(reservation.getCar().getPowerTrain())
                .rangeKm(reservation.getCar().getRangeKm())
                .topSpeedKmh(reservation.getCar().getTopSpeedKmh())
                .build();
        reservationResponseDto.setCarResponseDto(carResponseDto1);

        return reservationResponseDto;
    }
}
