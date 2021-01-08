package com.kielbiowski.parkproject.validation;

import com.kielbiowski.parkproject.dto.UserDTO;
import com.kielbiowski.parkproject.service.model.UserService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserDTOValidator implements Validator {

    private final UserService userService;

    public UserDTOValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;

        //email validation
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (userService.findByEmail(userDTO.getEmail()) != null) errors.rejectValue("email", "Email.duplicate");

        //password validation
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if(userDTO.getPassword().length()<8||userDTO.getPassword().length()>32) errors.rejectValue("password","Password.size");
        if(!userDTO.getPassword().equals(userDTO.getPasswordConfirm())) errors.rejectValue("password","Password.diff");
    }
}
