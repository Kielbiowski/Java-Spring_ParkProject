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
public class SpotDTO {
    private Integer id;
    private UserDTO userDTO;
    private ParkingDTO parkingDTO;

    @NotNull
    private Integer number;

    @NotNull
    private Size size;

    private CarDTO carDTO;
    private List<OfferDTO> offerDTOs;
    private List<TransactionDTO> transactionDTOs;

    public static Spot toSpot(SpotDTO spotDTO){
        return new Spot(
                spotDTO.getId(),
                UserDTO.toUser(spotDTO.getUserDTO()),
                ParkingDTO.toParking(spotDTO.getParkingDTO()),
                spotDTO.getNumber(),
                spotDTO.getSize(),
                CarDTO.toCar(spotDTO.getCarDTO()),
                //Null-safe streams done with Java 9' Stream.ofNullable
                Stream.ofNullable(spotDTO.getOfferDTOs())
                        .flatMap(Collection::stream)
                        .map(OfferDTO::toOffer)
                        .collect(Collectors.toList()),
                Stream.ofNullable(spotDTO.getTransactionDTOs())
                        .flatMap(Collection::stream)
                        .map(TransactionDTO::toTransaction)
                        .collect(Collectors.toList()));
    }

    public static SpotDTO toSpotDTO(Spot spot){
        return new SpotDTO(
                spot.getId(),
                UserDTO.toUserDTO(spot.getUser()),
                ParkingDTO.toParkingDTO(spot.getParking()),
                spot.getNumber(),
                spot.getSize(),
                CarDTO.toCarDTO(spot.getCar()),
                //Null-safe streams done with Java 8' Optionals
                Optional.ofNullable(spot.getOffers())
                        .stream()
                        .flatMap(Collection::stream)
                        .map(OfferDTO::toOfferDTO)
                        .collect(Collectors.toList()),
                Optional.ofNullable(spot.getTransactions())
                        .stream()
                        .flatMap(Collection::stream)
                        .map(TransactionDTO::toTransactionDTO)
                        .collect(Collectors.toList()));
    }
}
