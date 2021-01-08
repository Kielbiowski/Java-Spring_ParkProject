package com.kielbiowski.parkproject.dto;

import com.kielbiowski.parkproject.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Data
public class UserDTO {
    private Integer id;

    @NotNull
    @Email(message = "Provide valid email address!")
    private String email;

    @NotNull
    @Length(min = 8, message = "Password must be at least 8 characters long!")
    private String password;

    private String passwordConfirm;

    @NotNull
    private Integer accountBalance;

    private String name;
    private String surname;
    private Integer phoneNumber;
    private List<SpotDTO> spotDTOs;
    private List<CarDTO> carDTOs;
    private List<ParkingDTO> parkingDTOs;
    private List<RoleDTO> roleDTOs;

    public UserDTO() {
        this.accountBalance = 0;
    }

    public static User toUser(UserDTO userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getPasswordConfirm(),
                userDTO.getAccountBalance(),
                userDTO.getName(),
                userDTO.getSurname(),
                userDTO.getPhoneNumber(),
                //Null-safe streams done with Java 9' Stream.ofNullable
                Stream.ofNullable(userDTO.getSpotDTOs())
                        .flatMap(Collection::stream)
                        .map(SpotDTO::toSpot)
                        .collect(Collectors.toList()),
                Stream.ofNullable(userDTO.getCarDTOs())
                        .flatMap(Collection::stream)
                        .map(CarDTO::toCar)
                        .collect(Collectors.toList()),
                Stream.ofNullable(userDTO.getParkingDTOs())
                        .flatMap(Collection::stream)
                        .map(ParkingDTO::toParking)
                        .collect(Collectors.toList()),
                Stream.ofNullable(userDTO.getRoleDTOs())
                        .flatMap(Collection::stream)
                        .map(RoleDTO::toRole)
                        .collect(Collectors.toList()));
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getPasswordConfirm(),
                user.getAccountBalance(),
                user.getName(),
                user.getSurname(),
                user.getPhoneNumber(),
                //Null-safe streams done with Java 8' Optionals
                Optional.ofNullable(user.getSpots())
                        .stream()
                        .flatMap(Collection::stream)
                        .map(SpotDTO::toSpotDTO)
                        .collect(Collectors.toList()),
                Optional.ofNullable(user.getCars())
                        .stream()
                        .flatMap(Collection::stream)
                        .map(CarDTO::toCarDTO)
                        .collect(Collectors.toList()),
                Optional.ofNullable(user.getParkings())
                        .stream()
                        .flatMap(Collection::stream)
                        .map(ParkingDTO::toParkingDTO)
                        .collect(Collectors.toList()),
                Optional.ofNullable(user.getRoles())
                        .stream()
                        .flatMap(Collection::stream)
                        .map(RoleDTO::toRoleDTO)
                        .collect(Collectors.toList()));
    }
}
