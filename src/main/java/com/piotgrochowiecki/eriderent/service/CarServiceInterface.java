package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.request.CarCreateRequestDto;
import com.piotgrochowiecki.eriderent.dto.request.CarUpdateRequestDto;
import com.piotgrochowiecki.eriderent.dto.response.CarResponseDto;
import com.piotgrochowiecki.eriderent.exception.CarDeletionException;
import com.piotgrochowiecki.eriderent.exception.NoCarFoundException;

import java.time.LocalDate;
import java.util.List;

public interface CarServiceInterface {

    void add(CarCreateRequestDto carCreateRequestDto);

    void update(CarUpdateRequestDto carUpdateRequestDto);

    List<CarResponseDto> getAll();

    CarResponseDto getById(Long id) throws NoCarFoundException;

    List<CarResponseDto> getAvailableCars(LocalDate startDate, LocalDate endDate);

    CarResponseDto findCarByFullCarName(String brand, String model) throws NoCarFoundException;

    void deleteById(Long id) throws CarDeletionException;

}
