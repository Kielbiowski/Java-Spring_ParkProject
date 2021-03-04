package com.kielbiowski.parkproject.controller;

import com.kielbiowski.parkproject.dto.UserDTO;
import com.kielbiowski.parkproject.service.model.UserService;
import com.kielbiowski.parkproject.service.security.SecurityService;
import com.kielbiowski.parkproject.validation.UserDTOValidator;
import com.structurizr.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component(description = "Provides user register/login/logout and logged homepage.", technology = "Spring")
@UsedByContainer(name = "webApp",description = "uses")
@UsedByPerson(name = "User",description = "uses")
@UsedByContainer(name = "Database",description = "provides data")
@UsesContainer(name = "Database", description = "stores data")
@Controller
public class UserController {
    private final UserService userService;
    private final SecurityService securityService;
    private final UserDTOValidator userDTOValidator;

    @Autowired
    public UserController(UserService userService, SecurityService securityService, UserDTOValidator userDTOValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userDTOValidator = userDTOValidator;
    }

    @GetMapping(path = "/register")
    public String registerGet(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "userRegister";
    }

    @PostMapping(path = "/register")
    public String registerPost(Model model, UserDTO userDTO, BindingResult bindingResult) {
        userDTOValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) return "userRegister";

        userService.create(userDTO);
        model.addAttribute("success", true);
        model.addAttribute("userDTO", userDTO);
        securityService.autoLogin(userDTO.getEmail(), userDTO.getPasswordConfirm());
        return "userRegister";
    }

    @GetMapping(path = "/login")
    public String loginGet(Model model, String error, String logout) {
        if (error != null) model.addAttribute("error", "Your username or password is invalid.");
        if (logout != null) model.addAttribute("logout", "You have been logged out successfully.");
        return "userLogin";
    }

    @GetMapping(path = "/user")
    public String userGet(Model model) {
        UserDTO userDTO = userService.findByEmail(securityService.findLoggedInUsername());
        model.addAttribute("userDTO", userDTO);
        return "user";
    }

}
