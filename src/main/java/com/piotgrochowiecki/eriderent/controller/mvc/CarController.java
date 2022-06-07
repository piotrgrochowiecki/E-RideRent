package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.model.CarEntity;
import com.piotgrochowiecki.eriderent.service.JpaCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("car")
public class CarController {

    private final JpaCarService jpaCarService;

    @GetMapping("/findAll")
    private String showFindAll(Model model) {
        List<CarEntity> carList = jpaCarService.findAll();
        model.addAttribute("carList", carList);
        return "/carList.jsp";
    }

    @GetMapping("/edit/{id}")
    private String edit(@PathVariable Long id, Model model) {
        CarEntity car = jpaCarService.findById(id).get();
        model.addAttribute("car", car);
        return "/editCarForm.jsp";
    }

    @GetMapping("/deleteConfirmation/{id}")
    public String deleteConfirmation(@PathVariable Long id, Model model) {
        CarEntity car = jpaCarService.findById(id).get();
        model.addAttribute("car", car);
        return "/carDeleteConfirmation.jsp";
    }

    @GetMapping("/delete/{id}")
    private String deleteById(@PathVariable Long id) {
        jpaCarService.deleteById(id);
        return "/home.jsp";
    }
}
