package com.piotgrochowiecki.eriderent.dto.response;

import com.piotgrochowiecki.eriderent.dto.mapper.BaseMapper;
import com.piotgrochowiecki.eriderent.dto.request.CarUpdateRequestDto;
import com.piotgrochowiecki.eriderent.model.Car;
import com.piotgrochowiecki.eriderent.model.enumerator.PowerTrain;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class CarResponseDto extends BaseMapper {

    Long id;

    String uuid;

    String brand;

    String model;

    Double accelerationSec;

    Integer topSpeedKmh;

    Integer rangeKm;

    Integer fastChargeKmh;

    PowerTrain powerTrain;

    public String getFullCarName() {
        return this.brand + " " + this.model;
    }

    public static CarResponseDto map(Car car) {
        return map(car, CarResponseDto.class);
    }

    public static CarUpdateRequestDto map(CarResponseDto carResponseDto) {
        return map(carResponseDto, CarUpdateRequestDto.class);
    }
}
