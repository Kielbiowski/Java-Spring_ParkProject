package com.kielbiowski.parkproject.controller;

import com.kielbiowski.parkproject.dto.UserDTO;
import com.kielbiowski.parkproject.service.model.UserService;
import com.kielbiowski.parkproject.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;
    private final SecurityService securityService;

    @Autowired
    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping(path = "/user")
    public String userGet(Model model) {
        UserDTO userDTO = userService.findByEmail(securityService.findLoggedInUsername());
        model.addAttribute("userDTO", userDTO);
        return "user";
    }

    @GetMapping(path = "/user/details")
    public String userDetailsGet(Model model) {
        UserDTO userDTO = userService.findByEmail(securityService.findLoggedInUsername());
        model.addAttribute("userDTO", userDTO);
        return "userDetails";
    }

    @PostMapping
    public String userDetailsPost(Model model) {
        UserDTO userDTO = (UserDTO) model.getAttribute("userDTO");
        model.addAttribute("userDTO", userDTO);
        return "user";
    }

}
