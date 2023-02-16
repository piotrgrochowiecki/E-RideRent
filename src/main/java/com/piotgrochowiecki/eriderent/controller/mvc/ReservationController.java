package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.dto.ReservationDto;
import com.piotgrochowiecki.eriderent.model.Car;
import com.piotgrochowiecki.eriderent.model.User;
import com.piotgrochowiecki.eriderent.service.CarService;
import com.piotgrochowiecki.eriderent.service.ReservationService;
import com.piotgrochowiecki.eriderent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final CarService carService;
    private final UserService userService;
    private final HttpSession session;
    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @GetMapping("/chooseDates")
    public String showFormWithDates(Model model) {
        ReservationDto reservationDto = new ReservationDto();
        model.addAttribute("reservation", reservationDto);
        return "/reservationDatesCreate";
    }

    @PostMapping("/chooseDates")
    public String showAvailableCars(@ModelAttribute("reservation") @Valid ReservationDto reservationDto,
                                    BindingResult result,
                                    Model modelCars) {
        if (result.hasErrors()) {
            return "/reservationDatesCreate";
        }
        List<Car> availableCars = carService.findAvailableCars(reservationDto.getStartDate(), reservationDto.getEndDate());
        modelCars.addAttribute("availableCars", availableCars);
        return "/reservationCarCreate";
    }

    @PostMapping("/chooseCar")
    public String createReservation(@ModelAttribute("reservation") ReservationDto reservationDto) {
        reservationDto.setCar(reservationDto.getCar());
        String userEmail = (String) session.getAttribute("userEmail");
        Optional<User> user = userService.getByEmail(userEmail);
        reservationDto.setUser(user.orElseThrow());
        reservationService.add(reservationDto);
        logger.info("Reservation with dates " + reservationDto.getStartDate() + " and " + reservationDto.getEndDate()
                + " with car " + reservationDto.getCar().toString() + " has been created by user " + reservationDto.getUser().toString());
        return "reservationSuccess";
    }


}
