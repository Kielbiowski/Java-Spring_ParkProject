package com.kielbiowski.parkproject.validation;

import com.kielbiowski.parkproject.dto.UserDTO;
import com.kielbiowski.parkproject.service.model.UserService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {

    private final UserService userService;

    @Autowired
    public LoginValidator(UserService userService) {
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
        if (!errors.hasFieldErrors("email")) {
            if (!EmailValidator.getInstance().isValid(userDTO.getEmail())) errors.rejectValue("email", "Email.format");
            else if (userService.findByEmail(userDTO.getEmail()) != null) errors.rejectValue("email", "Email.duplicate");
        }

        //password validation
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (!errors.hasFieldErrors("password") && (userDTO.getPassword().length() < 8 || userDTO.getPassword().length() > 32))
            errors.rejectValue("password", "Password.size");

        //passwordConfirm validation
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "NotEmpty");
        if (!errors.hasFieldErrors("passwordConfirm") && !userDTO.getPassword().equals(userDTO.getPasswordConfirm()))
            errors.rejectValue("passwordConfirm", "Password.diff");
    }
}
