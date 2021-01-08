package com.kielbiowski.parkproject.controller;

import com.kielbiowski.parkproject.dto.UserDTO;
import com.kielbiowski.parkproject.service.model.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = {"/index", "/", ""})
    public String indexGet(Model model, @RequestParam(name = "id", required = false) Integer mockId) {
        UserDTO userDTO = null;
        if (mockId != null) {
            userDTO = userService.findById(mockId);
        }
        model.addAttribute("userDTO", userDTO);
        return "index";
    }

}
