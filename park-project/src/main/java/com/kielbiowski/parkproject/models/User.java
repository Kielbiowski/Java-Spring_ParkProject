package com.kielbiowski.parkproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Integer id;
    private String email;
    private String password;
    private Integer accountBalance;
    private String name;
    private String surname;
    private Integer phoneNumber;
}
