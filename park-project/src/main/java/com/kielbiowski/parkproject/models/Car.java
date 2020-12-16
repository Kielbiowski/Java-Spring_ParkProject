package com.kielbiowski.parkproject.models;

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
public class Car {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "spot_id",referencedColumnName = "id",unique = true)
    private Spot spot;

    @Column(nullable = false)
    private String numberPlate;

    @Column
    @Enumerated(EnumType.STRING)
    private Color color;

    @Column
    private String brand;

    @Column
    private String model;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Size size;

    @OneToMany(mappedBy = "car",fetch = FetchType.LAZY)
    private List<Request> requests;

    @OneToMany(mappedBy = "car",fetch = FetchType.LAZY)
    private List<Transaction> transactions;
}
