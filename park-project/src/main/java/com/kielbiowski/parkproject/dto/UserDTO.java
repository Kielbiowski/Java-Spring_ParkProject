package com.kielbiowski.parkproject.dto;

import com.kielbiowski.parkproject.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class UserDTO {
    private Integer id;

    @NotNull
    @Email(message = "Provide valid email address!")
    private String email;

    @NotNull
    @Length(min = 8,message = "Password must be at least 8 characters long!")
    private String password;

    @NotNull
    private Integer accountBalance;

    private String name;
    private String surname;
    private Integer phoneNumber;
    private List<SpotDTO> spotDTOs;
    private List<CarDTO> carDTOs;
    private List<ParkingDTO> parkingDTOs;

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
                userDTO.getSpotDTOs()
                        .stream()
                        .map(SpotDTO::toSpot)
                        .collect(Collectors.toList()),
                userDTO.getCarDTOs()
                        .stream()
                        .map(CarDTO::toCar)
                        .collect(Collectors.toList()),
                userDTO.getParkingDTOs()
                        .stream()
                        .map(ParkingDTO::toParking)
                        .collect(Collectors.toList()));
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
                user.getSpots()
                        .stream()
                        .map(SpotDTO::toSpotDTO)
                        .collect(Collectors.toList()),
                user.getCars()
                        .stream()
                        .map(CarDTO::toCarDTO)
                        .collect(Collectors.toList()),
                user.getParkings()
                        .stream()
                        .map(ParkingDTO::toParkingDTO)
                        .collect(Collectors.toList()));
    }
}
