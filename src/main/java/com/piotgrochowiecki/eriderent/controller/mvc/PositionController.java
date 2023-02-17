package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.dto.CarDto;
import com.piotgrochowiecki.eriderent.dto.PositionDto;
import com.piotgrochowiecki.eriderent.exception.NoCarFoundException;
import com.piotgrochowiecki.eriderent.exception.NoRecordedPositionException;
import com.piotgrochowiecki.eriderent.model.Car;
import com.piotgrochowiecki.eriderent.model.Position;
import com.piotgrochowiecki.eriderent.service.CarService;
import com.piotgrochowiecki.eriderent.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("car")
public class PositionController {

    private final CarService carService;
    private final PositionService positionService;
    private static final Logger logger = LoggerFactory.getLogger(PositionController.class);

    @GetMapping("/position/{id}")
    public String showCarPosition(@PathVariable Long id, Model model) throws NoCarFoundException, NoRecordedPositionException {
        Car car = carService.findById(id).orElseThrow(() -> new NoCarFoundException("No car with id " + id +
                " has been found"));
        CarDto carDto = CarDto.builder()
                    .model(car.getModel())
                    .brand(car.getBrand())
                    .build();
        model.addAttribute("car", carDto);

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

    @ExceptionHandler(NoCarFoundException.class)
    public String noCarFoundExceptionHandler() {
        logger.info("NoCarFoundException has been thrown!");
        return "noCarFoundEx";
    }

    @ExceptionHandler(NoRecordedPositionException.class)
    public String noRecordedPositionExceptionHandler() {
        logger.info("NoRecordedPositionException has been thrown!");
        return "noRecordedPositionEx";
    }

}
