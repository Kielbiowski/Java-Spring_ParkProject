package com.kielbiowski.parkproject.dto;

import com.kielbiowski.parkproject.model.Car;
import com.kielbiowski.parkproject.model.Parking;
import com.kielbiowski.parkproject.model.Spot;
import com.kielbiowski.parkproject.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@Data
public class UserDTO {

    private Integer id;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Length(min = 8)
    private String password;

    @NotNull
    private Integer accountBalance;

    private String name;
    private String surname;
    private Integer phoneNumber;
    private List<Spot> spots;
    private List<Car> cars;
    private List<Parking> parkings;

    public UserDTO() {
        this.accountBalance = 0;
    }

    public static User toUser(UserDTO userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getAccountBalance(),
                userDTO.getName(),
                userDTO.getSurname(),
                userDTO.getPhoneNumber(),
                userDTO.getSpots(),
                userDTO.getCars(),
                userDTO.getParkings());
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getAccountBalance(),
                user.getName(),
                user.getSurname(),
                user.getPhoneNumber(),
                user.getSpots(),
                user.getCars(),
                user.getParkings());
    }
}
