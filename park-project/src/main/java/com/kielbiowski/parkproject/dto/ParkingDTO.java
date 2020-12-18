package com.kielbiowski.parkproject.dto;

import com.kielbiowski.parkproject.model.Parking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkingDTO {
    private Integer id;

    @NotNull
    private String country;

    @NotNull
    private String zipCode;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String streetNumber;

    @NotNull
    private Integer pricePerHour;

    private Boolean adminAcceptance;
    private List<SpotDTO> spotDTOs;

    public static Parking toParking (ParkingDTO parkingDTO){
        return new Parking(
                parkingDTO.getId(),
                parkingDTO.getCountry(),
                parkingDTO.getZipCode(),
                parkingDTO.getCity(),
                parkingDTO.getStreet(),
                parkingDTO.getStreetNumber(),
                parkingDTO.getPricePerHour(),
                parkingDTO.getAdminAcceptance(),
                parkingDTO.getSpotDTOs()
                        .stream()
                        .map(SpotDTO::toSpot)
                        .collect(Collectors.toList()));
    }

    public static ParkingDTO toParkingDTO (Parking parking){
        return new ParkingDTO(
                parking.getId(),
                parking.getCountry(),
                parking.getZipCode(),
                parking.getCity(),
                parking.getStreet(),
                parking.getStreetNumber(),
                parking.getPricePerHour(),
                parking.getAdminAcceptance(),
                parking.getSpots()
                        .stream()
                        .map(SpotDTO::toSpotDTO)
                        .collect(Collectors.toList()));
    }
}
