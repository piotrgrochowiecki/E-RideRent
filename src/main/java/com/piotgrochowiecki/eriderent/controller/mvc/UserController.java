package com.piotgrochowiecki.eriderent.controller.mvc;

import com.piotgrochowiecki.eriderent.dto.response.UserResponseDto;
import com.piotgrochowiecki.eriderent.exception.NoUserFoundException;
import com.piotgrochowiecki.eriderent.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
@CommonsLog
public class UserController {

    private final UserService userService;

    @GetMapping("/findAll")
    private String showFindAll(Model model) {
        List<UserResponseDto> userList = userService.getAll();
        model.addAttribute("userList", userList);
        return "/userList";
    }

    @GetMapping("/edit/{id}")
    private String edit(@PathVariable Long id, Model model) throws NoUserFoundException {
        UserResponseDto userResponseDto = userService.getById(id);
        model.addAttribute("user", userResponseDto);
        return "/userEdit";
    }

    @PostMapping("/editConfirmation")
    private String editHandle(@ModelAttribute("user") @Valid UserResponseDto userResponseDto, BindingResult result) {
        if (result.hasErrors()) {
            return "/userEdit";
        }
        userService.update(userResponseDto);
        return "redirect:/user/findAll";
    }

    @GetMapping("/deleteConfirmation/{id}")
    public String deleteConfirmation(@PathVariable Long id, Model model) throws NoUserFoundException {
        UserResponseDto userResponseDto = userService.getById(id);
        model.addAttribute("user", userResponseDto);
        return "/userDeleteConfirmation";
    }

    @GetMapping("/delete/{id}")
    private String deleteById(@PathVariable Long id) throws NoUserFoundException {
        UserResponseDto userResponseDto = userService.getById(id);
        userService.deleteById(userResponseDto.getId());
        return "redirect:/user/findAll";
    }

    @ExceptionHandler(NoUserFoundException.class)
    public String noCarFoundExceptionHandler() {
        log.info("NoUserFoundException has been thrown!");
        return "/noUserFoundEx";
    }

}
