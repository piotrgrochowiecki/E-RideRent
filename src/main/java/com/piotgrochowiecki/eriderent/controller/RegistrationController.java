package com.piotgrochowiecki.eriderent.controller;

import com.piotgrochowiecki.eriderent.dto.request.UserRegisterRequestDto;
import com.piotgrochowiecki.eriderent.exception.EmailAlreadyExistsException;
import com.piotgrochowiecki.eriderent.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@CommonsLog
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        UserRegisterRequestDto userDto = new UserRegisterRequestDto();
        model.addAttribute("user", userDto);
        return "/registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegisterRequestDto userDto, BindingResult result)
            throws EmailAlreadyExistsException {
        log.info(userDto + " has been sent through the form.");
        if (result.hasErrors()) {
            log.info("Result has error" + result);
            return "/registration";
        }
        userService.registerNewAccount(userDto);
        return "/afterRegistration";
    }

}
