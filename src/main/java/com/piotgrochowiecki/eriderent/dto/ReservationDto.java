package com.piotgrochowiecki.eriderent.dto;

import com.piotgrochowiecki.eriderent.model.Car;
import com.piotgrochowiecki.eriderent.model.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
public class ReservationDto {

    private Long id;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private User user;

    private Car car;

}
