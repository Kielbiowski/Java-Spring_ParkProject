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
                spotDTO.getOfferDTOs()
                        .stream()
                        .map(OfferDTO::toOffer)
                        .collect(Collectors.toList()),
                spotDTO.getTransactionDTOs()
                        .stream()
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
                spot.getOffers()
                        .stream()
                        .map(OfferDTO::toOfferDTO)
                        .collect(Collectors.toList()),
                spot.getTransactions()
                        .stream()
                        .map(TransactionDTO::toTransactionDTO)
                        .collect(Collectors.toList()));
    }
}
