package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.APIclient.Position;


public interface PositionServiceInterface {

    String CURRENT_POSITION_ENDPOINT = "http://localhost:8090/api/position/current/";

    Position findCarsLatestPosition(String uuid);

}
