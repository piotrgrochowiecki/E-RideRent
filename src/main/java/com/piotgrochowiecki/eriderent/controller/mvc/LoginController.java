package com.piotgrochowiecki.eriderent.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "/login.jsp";
    }

    @GetMapping("/afterLogin")
    public String showAfterLogin() {
        return "/afterLogin.jsp";
    }

    @GetMapping("/logout")
    public String showLogout() {
        return "/logout.jsp";
    }

}
