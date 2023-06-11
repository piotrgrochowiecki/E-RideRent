package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.dto.request.CarCreateRequestDto;
import com.piotgrochowiecki.eriderent.dto.request.CarUpdateRequestDto;
import com.piotgrochowiecki.eriderent.dto.response.CarResponseDto;
import com.piotgrochowiecki.eriderent.exception.CarDeletionException;
import com.piotgrochowiecki.eriderent.exception.NoCarFoundException;
import com.piotgrochowiecki.eriderent.service.CarServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.piotgrochowiecki.eriderent.dto.response.CarResponseDto.map;

@Controller
@RequiredArgsConstructor
@RequestMapping("car")
@CommonsLog
public class CarController {

    private final CarServiceInterface carService;

    @GetMapping("/add")
    public String showCarAddForm(Model model) {
        CarCreateRequestDto carCreateRequestDto = new CarCreateRequestDto();
        model.addAttribute("carCreateRequestDto", carCreateRequestDto);
        return "/carCreate";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("carCreateRequestDto") @Valid CarCreateRequestDto carCreateRequestDto, BindingResult result) {
        if (result.hasErrors()) {
            return "/carCreate";
        }
        carService.add(carCreateRequestDto);
        return "redirect:/car/findAll";
    }

    @GetMapping("/edit/{id}")
    private String edit(@PathVariable Long id, Model model) throws NoCarFoundException {
        CarResponseDto carResponseDto = carService.getById(id);
        CarUpdateRequestDto carUpdateRequestDto = map(carResponseDto);
        model.addAttribute("carUpdateRequestDto", carUpdateRequestDto);
        return "/carEdit";
    }

    @PostMapping("/edit")
    private String editHandle(@ModelAttribute("carUpdateRequestDto") @Valid CarUpdateRequestDto carUpdateRequestDto, BindingResult result) {
        if (result.hasErrors()) {
            return "/carEdit";
        }
        carService.update(carUpdateRequestDto);
        return "redirect:/car/findAll";
    }

    @GetMapping("/findAll")
    private String showFindAll(Model model) {
        List<CarResponseDto> carList = carService.getAll();
        model.addAttribute("carList", carList);
        return "/carList";
    }

    @GetMapping("/tracking")
    public String tracking() {
        return "carPosition";
    }

    @GetMapping("/delete/{id}")
    private String deleteById(@PathVariable Long id) throws CarDeletionException {
        carService.deleteById(id);
        return "redirect:/car/findAll";
    }

    @GetMapping("/deleteConfirmation/{id}")
    public String deleteConfirmation(@PathVariable Long id, Model model) throws NoCarFoundException {
        CarResponseDto carResponseDto = carService.getById(id);
        model.addAttribute("carResponseDto", carResponseDto);
        return "/carDeleteConfirmation";
    }

    @ExceptionHandler(NoCarFoundException.class)
    public String noCarFoundExceptionHandler() {
        log.info("NoCarFoundException has been thrown!");
        return "/noCarFoundEx";
    }

    @ExceptionHandler(CarDeletionException.class)
    public String ConstraintViolationException() {
        log.info("CarDeletionException has been thrown!");
        return "/carDeletionEx";
    }

}