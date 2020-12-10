package com.kielbiowski.parkproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
    private Integer id;
    private Spot spot;
    private Car car;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer amount;
}
