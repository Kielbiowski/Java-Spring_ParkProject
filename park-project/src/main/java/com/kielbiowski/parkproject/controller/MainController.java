package com.kielbiowski.parkproject.controller;

import com.kielbiowski.parkproject.dto.UserDTO;
import com.kielbiowski.parkproject.service.model.UserService;
import com.kielbiowski.parkproject.service.security.SecurityService;
import com.kielbiowski.parkproject.validation.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final UserService userService;
    private final SecurityService securityService;
    private final RegistrationValidator registrationValidator;

    @Autowired
    public MainController(UserService userService, SecurityService securityService, RegistrationValidator registrationValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.registrationValidator = registrationValidator;
    }

    @GetMapping(path = {"/index", "/", ""})
    public String indexGet(Model model) {
        UserDTO userDTO = null;
        String email = securityService.findLoggedInUsername();
        if (email != null) {
            userDTO = userService.findByEmail(email);
        }
        model.addAttribute("userDTO", userDTO);
        return "index";
    }

    @GetMapping(path = "/register")
    public String registerGet(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @PostMapping(path = "/register")
    public String registerPost(Model model, UserDTO userDTO, BindingResult bindingResult) {
        registrationValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) return "register";

        userDTO = userService.create(userDTO);
        model.addAttribute("registration", true);
        model.addAttribute("userDTO", userDTO);
        securityService.autoLogin(userDTO.getEmail(), userDTO.getPasswordConfirm());
        return "userDetails";
    }

    @GetMapping(path = "/login")
    public String loginGet(Model model, String error, String logout) {
        if (error != null) model.addAttribute("error", "Your username or password is invalid.");
        if (logout != null) model.addAttribute("logout", "You have been logged out successfully.");
        return "login";
    }

}
