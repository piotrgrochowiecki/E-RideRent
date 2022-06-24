package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.model.CarEntity;
import com.piotgrochowiecki.eriderent.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@RequestMapping("car")
public class CreateCarController {

    private final CarService jpaCarService;

    @GetMapping("/add")
    public String showCarAddForm(Model model) {
        CarEntity car = new CarEntity();
        model.addAttribute("car", car);
        return "/carCreate";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("car") @Valid CarEntity car, BindingResult result) {
        if (result.hasErrors()) {
            return "/carCreate";
        }
        jpaCarService.addCar(car);
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

}
