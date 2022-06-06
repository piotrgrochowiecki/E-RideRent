package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.excepton.EmailExistsException;
import com.piotgrochowiecki.eriderent.excepton.PasswordsNotMatchingException;
import com.piotgrochowiecki.eriderent.model.CustomerEntity;
import com.piotgrochowiecki.eriderent.service.JpaCustomerService;
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

    private final JpaCustomerService jpaCustomerService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        CustomerEntity customer = new CustomerEntity();
        model.addAttribute("customer", customer);
        return "/registration.jsp";
    }

    @PostMapping("/registration")
    public String registerCustomerAccount(@ModelAttribute("customer") @Valid CustomerEntity customer, BindingResult result) {
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
