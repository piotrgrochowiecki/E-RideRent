package com.piotgrochowiecki.eriderent.dto.request;

import com.piotgrochowiecki.eriderent.dto.mapper.BaseMapper;
import com.piotgrochowiecki.eriderent.dto.response.CarResponseDto;
import com.piotgrochowiecki.eriderent.dto.response.UserResponseDto;
import com.piotgrochowiecki.eriderent.model.Car;
import com.piotgrochowiecki.eriderent.model.Reservation;
import com.piotgrochowiecki.eriderent.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationCreateRequestDto extends BaseMapper {

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;

    UserResponseDto userResponseDto;

    CarResponseDto carResponseDto;

    public static Reservation map(ReservationCreateRequestDto reservationCreateRequestDto) {
        Reservation reservation = map(reservationCreateRequestDto, Reservation.class);

        User user = User.builder()
                .id(reservationCreateRequestDto.getUserResponseDto().getId())
                .build();
        reservation.setUser(user);

        Car car = Car.builder()
                .id(reservationCreateRequestDto.getCarResponseDto().getId())
                .build();
        reservation.setCar(car);

        return reservation;
    }

}
