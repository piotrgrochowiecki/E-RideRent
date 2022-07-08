package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.model.Car;
import com.piotgrochowiecki.eriderent.model.Reservation;
import com.piotgrochowiecki.eriderent.service.CarService;
import com.piotgrochowiecki.eriderent.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final CarService carService;
    
    
    @GetMapping("/chooseDates")
    public String showFormWithDates(Model model) {
        Reservation reservation = new Reservation();
        model.addAttribute("reservation", reservation);
        return "/reservationDatesCreate";
    }

    @PostMapping("/chooseDates")
    public String showAvailableCars(@ModelAttribute("reservation") @Valid Reservation reservation,
                                    BindingResult result,
                                    Model modelCars) {
        if (result.hasErrors()) {
            return "/reservationDatesCreate";
        }
        List<Car> availableCars = carService.findAvailableCars(reservation.getStartDate(), reservation.getEndDate());
        modelCars.addAttribute("availableCars", availableCars);
        return "/reservationCarCreate";
    }

    @PostMapping("/chooseCar")
    public String createReservation(@ModelAttribute("reservation") Reservation reservation) {
        reservation.setCar(reservation.getCar());
        reservationService.add(reservation);
        return "reservationSuccess";
    }


}
