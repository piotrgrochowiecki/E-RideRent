package com.piotgrochowiecki.eriderent.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    private LocalDateTime time;

    @NotNull
    private Double longitude;

    @NotNull
    private Double latitude;
}
