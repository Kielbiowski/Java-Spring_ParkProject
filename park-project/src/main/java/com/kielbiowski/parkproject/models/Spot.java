package com.kielbiowski.parkproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Spot {
    private Integer id;
    private User user;
    private Parking parking;
    private Integer number;
    private String size;
}
