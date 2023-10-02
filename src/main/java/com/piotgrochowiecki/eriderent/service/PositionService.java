package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.APIclient.Position;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("PositionService")
@Slf4j
public class PositionService implements PositionServiceInterface {

    private final RestTemplate restTemplate;

    @Autowired
    public PositionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Position findCarsLatestPosition(String uuid) {
        return restTemplate.getForObject(CURRENT_POSITION_ENDPOINT + uuid, Position.class);
    }
}
