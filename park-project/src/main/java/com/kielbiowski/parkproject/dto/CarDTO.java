package com.kielbiowski.parkproject.dto;

import com.kielbiowski.parkproject.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

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
                carDTO.getRequestDTOs()
                        .stream()
                        .map(RequestDTO::toRequest)
                        .collect(Collectors.toList()),
                carDTO.getTransactionDTOs()
                        .stream()
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
                car.getRequests()
                        .stream()
                        .map(RequestDTO::toRequestDTO)
                        .collect(Collectors.toList()),
                car.getTransactions()
                        .stream()
                        .map(TransactionDTO::toTransactionDTO)
                        .collect(Collectors.toList()));
    }
}
