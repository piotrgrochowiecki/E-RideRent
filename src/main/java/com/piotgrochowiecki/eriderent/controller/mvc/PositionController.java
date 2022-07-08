package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.dto.CarDto;
import com.piotgrochowiecki.eriderent.dto.PositionDto;
import com.piotgrochowiecki.eriderent.exception.NoRecordedPositionException;
import com.piotgrochowiecki.eriderent.model.Car;
import com.piotgrochowiecki.eriderent.model.Position;
import com.piotgrochowiecki.eriderent.service.CarService;
import com.piotgrochowiecki.eriderent.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("car")
public class PositionController {

    private final CarService carService;
    private final PositionService positionService;

    @GetMapping("/position/{id}")
    public String showCarPosition(@PathVariable Long id, Model model) {
        Optional<Car> car = carService.findById(id);
        CarDto carDto = CarDto.builder()
                .model(car.get().getModel())
                .brand(car.get().getBrand())
                .build();

        try {
            Optional<Position> position = positionService.findCarsLatestPosition(carDto);
            PositionDto positionDto = PositionDto.builder()
                    .latitude(position.get().getLatitude())
                    .longitude(position.get().getLongitude())
                    .build();
            model.addAttribute("position", positionDto);
            return "carPosition";
        } catch (NoRecordedPositionException e) {
            return "noRecordedPositionEx";
        }

    }

}
