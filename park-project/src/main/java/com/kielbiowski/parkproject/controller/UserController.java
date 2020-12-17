package com.kielbiowski.parkproject.controller;

import com.kielbiowski.parkproject.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.kielbiowski.parkproject.service.UserService;

import javax.validation.Valid;
import java.awt.*;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/register")
    public String registerGet(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "userRegister";
    }

    @PostMapping(path = "/register")
    public String registerPost(Model model, @Valid UserDTO userDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()
                && !userDTO.getEmail().isEmpty()
                && !userDTO.getPassword().isEmpty()
                && userDTO.getAccountBalance().equals(0)) {
            userService.create(userDTO);
            model.addAttribute("success", true);
        }
        model.addAttribute("userDTO", userDTO);
        return "userRegister";
    }

    @GetMapping(path = "/user")
    public String userGet(Model model, @RequestParam(name = "id", required = true) Integer mockId) {
        model.addAttribute("userDTO", userService.findById(mockId));
        return "user";
    }
}
