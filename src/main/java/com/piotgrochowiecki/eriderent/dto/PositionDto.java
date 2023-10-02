package com.piotgrochowiecki.eriderent.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
public class PositionDto {

    @NotNull
    private Long id;

    @NotNull
    private LocalTime time;

    private LocalDate date;

    @NotNull
    private Double longitude;

    @NotNull
    private Double latitude;
}
