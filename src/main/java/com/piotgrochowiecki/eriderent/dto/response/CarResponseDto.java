package com.piotgrochowiecki.eriderent.dto.response;

import com.piotgrochowiecki.eriderent.dto.mapper.BaseMapper;
import com.piotgrochowiecki.eriderent.dto.request.CarUpdateRequestDto;
import com.piotgrochowiecki.eriderent.model.Car;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    String brand;

    String model;

    Double accelerationSec;

    Integer topSpeedKmh;

    Integer rangeKm;

    Integer fastChargeKmh;

    String powerTrain;

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
