package com.kielbiowski.parkproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Car {
    private Integer id;
    private User user;
    private String numberPlate;
    private Color color;
    private String brand;
    private String model;
    private Size size;
    private Spot spot;
}
