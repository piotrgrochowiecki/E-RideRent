package com.piotgrochowiecki.eriderent.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
@Setter @Getter @Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    //all wheel drive, front wheel drive, rear wheel drive

    @OneToMany(mappedBy = "car")
    @ToString.Exclude
    private List<Reservation> reservationList =
            new ArrayList<>();
    //One car to many reservations

    @OneToMany(mappedBy = "car")
    @ToString.Exclude
    private List<Review> reviewList = new ArrayList<>();
    //One car to many reviews

    public String getFullCarName() {
        return brand + " " + model;
    }
}
