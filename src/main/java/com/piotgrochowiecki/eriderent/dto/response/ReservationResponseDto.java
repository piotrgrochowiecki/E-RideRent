package com.piotgrochowiecki.eriderent.dto.response;

import com.piotgrochowiecki.eriderent.dto.mapper.BaseMapper;
import com.piotgrochowiecki.eriderent.model.Reservation;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationResponseDto extends BaseMapper {

    Long id;

    LocalDate startDate;

    LocalDate endDate;

    public static ReservationResponseDto map(Reservation reservation) {
        return map(reservation, ReservationResponseDto.class);
    }
}
