package com.piotgrochowiecki.eriderent.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "cars")
@Setter @Getter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank @DecimalMin(value = "0.0", inclusive = false) @DecimalMax(value = "100.0")
    @Digits(integer = 2, fraction = 1)
    private Double accelerationSec;

    @NotBlank @Max(500) @Min(1)
    private Integer topSpeedKmh;

    @NotBlank @Max(3000) @Min(1)
    private Integer rangeKm;

    @NotBlank @Max(3000) @Min(1)
    private Integer fastChargeKmh;

    @NotBlank @Pattern(regexp = "awd|fwd|rwd", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String powerTrain;
    //all wheel drive, front wheel drive, rear wheel drive


}
