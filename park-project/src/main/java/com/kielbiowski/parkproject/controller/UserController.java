package com.kielbiowski.parkproject.controller;

import com.kielbiowski.parkproject.dto.UserDTO;
import com.kielbiowski.parkproject.service.model.UserService;
import com.kielbiowski.parkproject.service.security.SecurityService;
import com.kielbiowski.parkproject.validation.UserDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;
    private final SecurityService securityService;
    private final UserDataValidator userDataValidator;

    @Autowired
    public UserController(UserService userService, SecurityService securityService, UserDataValidator userDataValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userDataValidator = userDataValidator;
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

    @PostMapping(path = "/user/details")
    public String userDetailsPost(Model model, UserDTO userDTO, BindingResult bindingResult) {
        userDataValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) return "userDetails";

        UserDTO entity = userService.findByEmail(securityService.findLoggedInUsername());
        entity.setName(userDTO.getName());
        entity.setSurname(userDTO.getSurname());
        entity.setPhoneNumber(userDTO.getPhoneNumber());
        userDTO = userService.update(entity);

        model.addAttribute("userDTO",userDTO);
        model.addAttribute("success", true);
        return "user";
    }

}
