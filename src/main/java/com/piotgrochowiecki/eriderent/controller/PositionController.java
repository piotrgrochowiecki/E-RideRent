package com.piotgrochowiecki.eriderent.controller;

import com.piotgrochowiecki.eriderent.dto.PositionDto;
import com.piotgrochowiecki.eriderent.dto.response.CarResponseDto;
import com.piotgrochowiecki.eriderent.exception.NoCarFoundException;
import com.piotgrochowiecki.eriderent.exception.NoRecordedPositionException;
import com.piotgrochowiecki.eriderent.model.Position;
import com.piotgrochowiecki.eriderent.service.CarService;
import com.piotgrochowiecki.eriderent.service.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("car")
@CommonsLog
public class PositionController {

    private final CarService carService;
    private final PositionService positionService;

    @GetMapping("/position/{id}")
    public String showCarPosition(@PathVariable Long id, Model model) throws NoCarFoundException, NoRecordedPositionException {
        CarResponseDto carResponseDto = carService.getById(id);
        model.addAttribute("car", carResponseDto);

        Position position = positionService.findCarsLatestPosition(id).orElseThrow(() -> new NoRecordedPositionException("No " +
                "recorder position for this car has been found!"));
        PositionDto positionDto = PositionDto.builder()
                .latitude(position.getLatitude())
                .longitude(position.getLongitude())
                .time(position.getTime())
                .build();
        model.addAttribute("position", positionDto);
        return "carPosition";
    }

}
