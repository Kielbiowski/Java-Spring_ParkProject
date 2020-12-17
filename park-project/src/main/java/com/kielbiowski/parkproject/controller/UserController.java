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

    @PostMapping(path = "/register",produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String registerPost(@Valid UserDTO userDTO, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()){
            userService.create(userDTO);
            return "Zarejestrowano pomyślnie";
        }
        return "Operacja zakończona niepowodzeniem";
    }

}
