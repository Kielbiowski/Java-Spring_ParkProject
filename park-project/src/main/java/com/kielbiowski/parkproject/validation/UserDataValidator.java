package com.kielbiowski.parkproject.validation;

import com.kielbiowski.parkproject.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserDataValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;

        //name validation
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");

        //surname validation
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "NotEmpty");

        //passwordConfirm validation
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "NotEmpty");
        //Here I assume the phone number length equals 9, as is in Poland.
        if (!errors.hasFieldErrors("phoneNumber") && userDTO.getPhoneNumber().toString().length()!=9)
            errors.rejectValue("phoneNumber", "PhoneNumber.format");
    }
}
