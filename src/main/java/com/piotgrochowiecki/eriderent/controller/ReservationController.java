package com.piotgrochowiecki.eriderent.controller;

import com.piotgrochowiecki.eriderent.dto.request.ReservationCreateRequestDto;
import com.piotgrochowiecki.eriderent.dto.response.CarResponseDto;
import com.piotgrochowiecki.eriderent.exception.NoCarFoundException;
import com.piotgrochowiecki.eriderent.exception.NoUserFoundException;
import com.piotgrochowiecki.eriderent.service.CarServiceInterface;
import com.piotgrochowiecki.eriderent.service.ReservationServiceInterface;
import com.piotgrochowiecki.eriderent.service.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
@CommonsLog
public class ReservationController {

    private final ReservationServiceInterface reservationService;
    private final CarServiceInterface carService;
    private final UserServiceInterface userService;

    @GetMapping("/chooseDates")
    public String showFormWithDates(Model model) {
        ReservationCreateRequestDto reservationCreateRequestDto = new ReservationCreateRequestDto();
        model.addAttribute("reservation", reservationCreateRequestDto);
        return "/reservationDatesCreate";
    }

    @PostMapping("/chooseDates")
    public String showAvailableCars(@ModelAttribute("reservation") @Valid ReservationCreateRequestDto reservationCreateRequestDto,
                                    BindingResult result,
                                    Model modelCars) {
        if (result.hasErrors()) {
            log.info("Reservation request has errors: " + reservationCreateRequestDto.toString());
            return "/reservationDatesCreate";
        }
        List<CarResponseDto> availableCars = carService.getAvailableCars(reservationCreateRequestDto.getStartDate(), reservationCreateRequestDto.getEndDate());
        modelCars.addAttribute("availableCars", availableCars);
        return "/reservationCarCreate";
    }

    @PostMapping("/chooseCar")
    public String createReservation(@ModelAttribute("reservation") ReservationCreateRequestDto reservationCreateRequestDto,
                                    @AuthenticationPrincipal UserDetails userDetails) throws NoCarFoundException, NoUserFoundException {

        reservationCreateRequestDto.setCarResponseDto(carService.getById(reservationCreateRequestDto.getCarResponseDto().getId()));
        reservationCreateRequestDto.setUserResponseDto(userService.getByEmail(userDetails.getUsername()));
        reservationService.add(reservationCreateRequestDto);

        log.info("Reservation with dates " + reservationCreateRequestDto.getStartDate() + " and " + reservationCreateRequestDto.getEndDate()
                + " with car " + reservationCreateRequestDto.getCarResponseDto().getFullCarName() + " has been created by user "
                + reservationCreateRequestDto.getUserResponseDto().getEmail() + " with id " + reservationCreateRequestDto.getUserResponseDto().getId());

        return "reservationSuccess";
    }

}
