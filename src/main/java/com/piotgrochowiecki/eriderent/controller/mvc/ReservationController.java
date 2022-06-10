package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.model.ReservationEntity;
import com.piotgrochowiecki.eriderent.service.JpaCarService;
import com.piotgrochowiecki.eriderent.service.JpaReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

    @PostMapping("/chooseDates")
    @ResponseBody
    public String showOverlappingReservations(@ModelAttribute("reservation") ReservationEntity reservation) {
        List<ReservationEntity> existingReservationsOverlappingWithNewReservation =
                jpaReservationService.findAllReservationsOverlappingWithDates(reservation.getStartDate(), reservation.getEndDate());
        return Arrays.toString(existingReservationsOverlappingWithNewReservation.toArray());
    }

//    @PostMapping("chooseReservation")
//    public String showFormWithReservations(@ModelAttribute("reservation") ReservationEntity reservation, BindingResult result,
//                                   Model model) {
//        if (result.hasErrors()) {
//            return "reservationDatesCreate.jsp";
//        }
//        //napisać w jpaReservationService metodę pobierającą wolne samochody w danym terminie
//        //pobrać listę takich samochodów i dodać do modelu
//    }

}
