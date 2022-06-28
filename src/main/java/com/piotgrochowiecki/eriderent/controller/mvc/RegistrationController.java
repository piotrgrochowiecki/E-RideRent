package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.model.Role;
import com.piotgrochowiecki.eriderent.model.User;
import com.piotgrochowiecki.eriderent.service.RoleService;
import com.piotgrochowiecki.eriderent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/registration";
    }

    @ModelAttribute("roleList")
    public Collection<Role> roles() {
        return roleService.findAll();
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/registration";
        }
        userService.add(user);
        return "/afterRegistration";
    }

}
