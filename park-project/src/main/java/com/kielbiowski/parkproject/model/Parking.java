package com.kielbiowski.parkproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Parking {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String streetNumber;

    @Column(nullable = false)
    private Integer pricePerHour;

    @Column
    private Boolean adminAcceptance;

    @OneToMany(mappedBy = "parking", fetch = FetchType.LAZY)
    private List<Spot> spots;
}
