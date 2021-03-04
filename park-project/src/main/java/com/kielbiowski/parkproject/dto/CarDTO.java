package com.kielbiowski.parkproject.dto;

import com.kielbiowski.parkproject.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDTO {
    private Integer id;
    private UserDTO userDTO;
    private SpotDTO spotDTO;

    @NotNull
    private String numberPlate;

    private Color color;
    private String brand;
    private String model;

    public CarDTO(Integer id) {
        this.id = id;
    }

    @NotNull
    private Size size;
    private List<RequestDTO> requestDTOs;
    private List<TransactionDTO> transactionDTOs;

    public static Car toCar(CarDTO carDTO) {
        return new Car(
                carDTO.getId(),
                UserDTO.toUser(carDTO.getUserDTO()),
                SpotDTO.toSpot(carDTO.getSpotDTO()),
                carDTO.getNumberPlate(),
                carDTO.getColor(),
                carDTO.getBrand(),
                carDTO.getModel(),
                carDTO.getSize(),
                //Null-safe streams done with Java 9' Stream.ofNullable
                Stream.ofNullable(carDTO.getRequestDTOs())
                        .flatMap(Collection::stream)
                        .map(RequestDTO::toRequest)
                        .collect(Collectors.toList()),
                Stream.ofNullable(carDTO.getTransactionDTOs())
                        .flatMap(Collection::stream)
                        .map(TransactionDTO::toTransaction)
                        .collect(Collectors.toList()));
    }

    public static CarDTO toCarDTO(Car car) {
        return new CarDTO(
                car.getId(),
                UserDTO.toUserDTO(car.getUser()),
                SpotDTO.toSpotDTO(car.getSpot()),
                car.getNumberPlate(),
                car.getColor(),
                car.getBrand(),
                car.getModel(),
                car.getSize(),
                //Null-safe streams done with Java 8' Optionals
                Optional.ofNullable(car.getRequests())
                        .stream()
                        .flatMap(Collection::stream)
                        .map(RequestDTO::toRequestDTO)
                        .collect(Collectors.toList()),
                Optional.ofNullable(car.getTransactions())
                        .stream()
                        .flatMap(Collection::stream)
                        .map(TransactionDTO::toTransactionDTO)
                        .collect(Collectors.toList()));
    }
}
