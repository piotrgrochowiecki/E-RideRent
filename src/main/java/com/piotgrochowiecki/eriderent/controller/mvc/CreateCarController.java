package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.dto.CarDto;
import com.piotgrochowiecki.eriderent.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@RequestMapping("car")
public class CreateCarController {

    private final CarService carService;

    @GetMapping("/add")
    public String showCarAddForm(Model model) {
        CarDto carDto = new CarDto();
        model.addAttribute("car", carDto);
        return "/carCreate";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("car") @Valid CarDto carDto, BindingResult result) {
        if (result.hasErrors()) {
            return "/carCreate";
        }
        carService.add(carDto);
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
