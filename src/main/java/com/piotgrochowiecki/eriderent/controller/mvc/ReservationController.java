package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.model.ReservationEntity;
import com.piotgrochowiecki.eriderent.service.JpaCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final JpaCarService jpaCarService;

    @GetMapping("/chooseDates")
    public String showFormWithDates(Model model) {
        ReservationEntity reservation = new ReservationEntity();
        model.addAttribute("reservation", reservation);
        return "/reservationDatesCreate.jsp";
    }

//    @PostMapping("chooseCar")
//    public String showFormWithCars(@ModelAttribute("reservation") ReservationEntity reservation, BindingResult result,
//                                   Model model) {
//        if (result.hasErrors()) {
//            return "reservationDatesCreate.jsp";
//        }
//        //napisać w jpaCarService metodę pobierającą wolne samochody w danym terminie
//        //pobrać listę takich samochodów i dodać do modelu
//    }

}
