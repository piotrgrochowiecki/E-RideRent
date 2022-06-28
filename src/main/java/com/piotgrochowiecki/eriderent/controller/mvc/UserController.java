package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.model.User;
import com.piotgrochowiecki.eriderent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("/findAll")
    private String showFindAll(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "/userList";
    }

    @GetMapping("/edit/{id}")
    private String edit(@PathVariable Long id, Model model) {
        User user = userService.findById(id).get();
        model.addAttribute("user", user);
        return "/userEdit";
    }

    @PostMapping("/editConfirmation")
    private String editHandle(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/userEdit";
        }
        userService.update(user);
        return "redirect:/user/findAll";
    }

    @GetMapping("/deleteConfirmation/{id}")
    public String deleteConfirmation(@PathVariable Long id, Model model) {
        User user = userService.findById(id).get();
        model.addAttribute("user", user);
        return "/userDeleteConfirmation";
    }

    @GetMapping("/delete/{id}")
    private String deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/user/findAll";
    }

}
