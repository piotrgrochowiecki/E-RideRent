package com.piotgrochowiecki.eriderent.APIclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Position(Long id, LocalDate date, LocalTime time, Double longitude, Double latitude, Car car) {

}
