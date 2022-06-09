package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.model.UserEntity;
import com.piotgrochowiecki.eriderent.service.JpaUserService;
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

    private final JpaUserService jpaUserService;

    @GetMapping("/findAll")
    private String showFindAll(Model model) {
        List<UserEntity> userList = jpaUserService.findAll();
        model.addAttribute("userList", userList);
        return "/userList.jsp";
    }

    @GetMapping("/edit/{id}")
    private String edit(@PathVariable Long id, Model model) {
        UserEntity user = jpaUserService.findById(id).get();
        model.addAttribute("user", user);
        return "/userEdit.jsp";
    }

    @PostMapping("/editConfirmation")
    private String editHandle(@ModelAttribute("user") @Valid UserEntity user, BindingResult result) {
        if (result.hasErrors()) {
            return "/userEdit.jsp";
        }
        jpaUserService.update(user);
        return "redirect:/user/findAll";
    }

    @GetMapping("/deleteConfirmation/{id}")
    public String deleteConfirmation(@PathVariable Long id, Model model) {
        UserEntity user = jpaUserService.findById(id).get();
        model.addAttribute("user", user);
        return "/userDeleteConfirmation.jsp";
    }

    @GetMapping("/delete/{id}")
    private String deleteById(@PathVariable Long id) {
        jpaUserService.deleteById(id);
        return "redirect:/user/findAll";
    }

}
