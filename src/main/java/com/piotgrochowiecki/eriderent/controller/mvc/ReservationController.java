package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.model.CarEntity;
import com.piotgrochowiecki.eriderent.model.ReservationEntity;
import com.piotgrochowiecki.eriderent.service.JpaCarService;
import com.piotgrochowiecki.eriderent.service.JpaReservationService;
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

    private final JpaReservationService jpaReservationService;
    private final JpaCarService jpaCarService;

//    @GetMapping("/findAll")
//    private String showFindAll(Model model) {
//        List<ReservationEntity> reservationList = jpaReservationService.findAll();
//        model.addAttribute("reservationList", reservationList);
//        return "/reservationList";
//    }
    

    @GetMapping("/chooseDates")
    public String showFormWithDates(Model model) {
        ReservationEntity reservation = new ReservationEntity();
        model.addAttribute("reservation", reservation);
        return "/reservationDatesCreate";
    }

//    @ModelAttribute("availableCars")
//    public Collection<CarEntity> availableCars(ReservationEntity reservation) {
//        Collection<CarEntity> availableCars = jpaCarService.findAvailableCars(reservation.getStartDate(), reservation.getEndDate());
//        return availableCars;
//    }

    @PostMapping("/chooseDates")
    public String showAvailableCars(@ModelAttribute("reservation") @Valid ReservationEntity reservation,
                                    BindingResult result,
                                    Model modelCars) {
        if (result.hasErrors()) {
            return "/reservationDatesCreate";
        }
        List<CarEntity> availableCars = jpaCarService.findAvailableCars(reservation.getStartDate(), reservation.getEndDate());
        modelCars.addAttribute("availableCars", availableCars);
//        modelReservation.addAttribute("reservation", reservation);
        return "/reservationCarCreate";
    }

    @PostMapping("/chooseCar")
    public String createReservation(@ModelAttribute("reservation") ReservationEntity reservation,
                                    @ModelAttribute("availableCars") List<CarEntity> oneCarList) {
        CarEntity car = oneCarList.get(0);
        reservation.setCar(car);
        jpaReservationService.add(reservation);
        return "reservationSuccess";
    }


}
