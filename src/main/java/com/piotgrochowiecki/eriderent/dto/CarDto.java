package com.piotgrochowiecki.eriderent.dto;

import lombok.*;

import javax.validation.constraints.*;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
public class CarDto {

    @NotBlank(message = "{validation.error.notBlank}")
    private String brand;

    @NotBlank(message = "{validation.error.notBlank}")
    private String model;

    @NotNull(message = "{validation.error.notBlank}") @DecimalMin(value = "0.0", inclusive = false) @DecimalMax(value = "100.0")
    @Digits(integer = 2, fraction = 1)
    private Double accelerationSec;

    @NotNull(message = "{validation.error.notBlank}") @Max(500) @Min(1)
    private Integer topSpeedKmh;

    @NotNull(message = "{validation.error.notBlank}") @Max(3000) @Min(1)
    private Integer rangeKm;

    @NotNull(message = "{validation.error.notBlank}") @Max(3000) @Min(1)
    private Integer fastChargeKmh;

    @NotBlank(message = "{validation.error.notBlank}") @Pattern(regexp = "awd|fwd|rwd", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String powerTrain;

}
