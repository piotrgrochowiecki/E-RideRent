package com.piotgrochowiecki.eriderent.dto.request;

import com.piotgrochowiecki.eriderent.dto.mapper.BaseMapper;
import com.piotgrochowiecki.eriderent.model.Car;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class CarUpdateRequestDto extends BaseMapper {

    Long id;

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

    @NotBlank(message = "{validation.error.notBlank}")
    @Pattern(regexp = "awd|fwd|rwd", flags = Pattern.Flag.CASE_INSENSITIVE)
    String powerTrain;

    public static Car map(CarUpdateRequestDto carUpdateRequestDto) {
        return map(carUpdateRequestDto, Car.class);
    }
    
}
