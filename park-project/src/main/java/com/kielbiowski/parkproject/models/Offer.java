package com.kielbiowski.parkproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Offer {
    private Integer id;
    private Spot spot;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
