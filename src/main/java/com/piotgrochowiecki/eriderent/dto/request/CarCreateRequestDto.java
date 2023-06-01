package com.piotgrochowiecki.eriderent.dto.request;

import com.piotgrochowiecki.eriderent.dto.mapper.BaseMapper;
import com.piotgrochowiecki.eriderent.model.Car;
import com.piotgrochowiecki.eriderent.model.enumerator.PowerTrain;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class CarCreateRequestDto extends BaseMapper {

    @NotBlank(message = "{validation.error.notBlank}")
    String brand;

    @NotBlank(message = "{validation.error.notBlank}")
    String model;

    @NotNull(message = "{validation.error.notBlank}")
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "100.0")
    @Digits(integer = 2, fraction = 1)
    Double accelerationSec;

    @NotNull(message = "{validation.error.notBlank}")
    @Max(500)
    @Min(1)
    Integer topSpeedKmh;

    @NotNull(message = "{validation.error.notBlank}")
    @Max(3000)
    @Min(1)
    Integer rangeKm;

    @NotNull(message = "{validation.error.notBlank}")
    @Max(3000)
    @Min(1)
    Integer fastChargeKmh;

    @NotNull(message = "{validation.error.notBlank}")
    @Enumerated(EnumType.STRING)
    PowerTrain powerTrain;

    public String getFullCarName() {
        return this.brand + " " + this.model;
    }

    public static Car map(CarCreateRequestDto carCreateRequestDto) {
        return map(carCreateRequestDto, Car.class);
    }

}
