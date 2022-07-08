package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.dto.CarDto;
import com.piotgrochowiecki.eriderent.model.Car;
import com.piotgrochowiecki.eriderent.service.CarService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/findAll")
    private String showFindAll(Model model) {
        List<Car> carList = carService.findAll();
        model.addAttribute("carList", carList);
        return "/carList";
    }

    @GetMapping("/edit/{id}")
    private String edit(@PathVariable Long id, Model model) {
        Car car = carService.findById(id).get();
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
    public String deleteConfirmation(@PathVariable Long id, Model model) {
        Car car = carService.findById(id).get();
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
}
