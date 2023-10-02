package com.piotgrochowiecki.eriderent.APIclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Car(String uuid, String brand, String model, List<Position> positionList) {

}
