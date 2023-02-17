package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.dto.CarDto;
import com.piotgrochowiecki.eriderent.exception.NoCarFoundException;
import com.piotgrochowiecki.eriderent.model.Car;
import com.piotgrochowiecki.eriderent.service.CarService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("car")
public class CarController {

    private final CarService carService;

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);


    @GetMapping("/findAll")
    private String showFindAll(Model model) {
        List<Car> carList = carService.findAll();
        model.addAttribute("carList", carList);
        return "/carList";
    }

    @GetMapping("/edit/{id}")
    private String edit(@PathVariable Long id, Model model) throws NoCarFoundException {
        Car car = carService.findById(id).orElseThrow(() -> new NoCarFoundException("No car with id " + id +
                " has been found"));
        CarDto carDto = new CarDto(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getAccelerationSec(),
                car.getTopSpeedKmh(),
                car.getRangeKm(),
                car.getFastChargeKmh(),
                car.getPowerTrain());
        model.addAttribute("car", carDto);
        return "/carEdit";
    }

    @PostMapping("/edit")
    private String editHandle(@ModelAttribute("car") @Valid CarDto carDto, BindingResult result) {
        if (result.hasErrors()) {
            return "/carEdit";
        }
        carService.add(carDto);
        return "redirect:/car/findAll";
    }

    @GetMapping("/deleteConfirmation/{id}")
    public String deleteConfirmation(@PathVariable Long id, Model model) throws NoCarFoundException {
        Car car = carService.findById(id).orElseThrow(() -> new NoCarFoundException("No car with id " + id +
                " has been found"));
        CarDto carDto = new CarDto(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getAccelerationSec(),
                car.getTopSpeedKmh(),
                car.getRangeKm(),
                car.getFastChargeKmh(),
                car.getPowerTrain());
        model.addAttribute("car", carDto);
        return "/carDeleteConfirmation";
    }

    @ExceptionHandler(NoCarFoundException.class)
    public String noCarFoundExceptionHandler() {
        logger.info("NoCarFoundException has been thrown!");
        return "/noCarFoundEx";
    }

    @GetMapping("/delete/{id}")
    private String deleteById(@PathVariable Long id) {
        carService.deleteById(id);
        return "redirect:/car/findAll";
    }

    @ModelAttribute("powerTrainTypes")
    public HashMap<String, String> powerTrain() {
        HashMap<String, String> powerTrainTypes = new HashMap<>();
        powerTrainTypes.put("awd", "All wheel drive");
        powerTrainTypes.put("rwd", "Rear wheel drive");
        powerTrainTypes.put("fwd", "Front wheel drive");
        return powerTrainTypes;
    }

    @GetMapping("/tracking")
    public String tracking() {
        return "carPosition";
    }

    @GetMapping("/add")
    public String showCarAddForm(Model model) {
        CarDto carDto2 = new CarDto();
        model.addAttribute("car2", carDto2);
        return "/carCreate";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("car2") @Valid CarDto carDto2, BindingResult result) {
        if (result.hasErrors()) {
            logger.info("An error occurred while adding a car to database.");
            logger.info(result.getAllErrors().toString());
            return "/carCreate";
        }
        carService.add(carDto2);
        logger.info("Car " + carDto2.getFullCarName() + " has been successfully added to database.");
        return "redirect:/car/findAll";
    }
}