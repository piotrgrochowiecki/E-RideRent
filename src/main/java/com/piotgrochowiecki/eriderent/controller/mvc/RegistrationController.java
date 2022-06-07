package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.excepton.EmailExistsException;
import com.piotgrochowiecki.eriderent.excepton.PasswordsNotMatchingException;
import com.piotgrochowiecki.eriderent.model.UserEntity;
import com.piotgrochowiecki.eriderent.service.JpaUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final JpaUserService jpaCustomerService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        UserEntity customer = new UserEntity();
        model.addAttribute("customer", customer);
        return "/registration.jsp";
    }

    @PostMapping("/registration")
    public String registerCustomerAccount(@ModelAttribute("customer") @Valid UserEntity customer, BindingResult result) {
        if (result.hasErrors()) {
            return "/registration.jsp";
        }
        try {
            jpaCustomerService.registerCustomer(customer);
            return "/registrationSuccessful.jsp";
        } catch (PasswordsNotMatchingException e) {
            return "/passwordNotMatching.jsp";
        } catch (EmailExistsException e2) {
            return "/emailAlreadyExists.jsp";
        }

    }

}
