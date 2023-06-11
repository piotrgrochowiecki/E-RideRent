package com.piotgrochowiecki.eriderent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "/login";
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "/dashboard";
    }

    @GetMapping("/logout")
    public String showLogout() {
        return "/logout";
    }

}
