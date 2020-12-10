package com.kielbiowski.parkproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Parking {
    private Integer id;
    private String country;
    private String zipCode;
    private String city;
    private String street;
    private String streetNumber;
    private Integer pricePerHour;
    private Boolean adminAcceptance;
}
