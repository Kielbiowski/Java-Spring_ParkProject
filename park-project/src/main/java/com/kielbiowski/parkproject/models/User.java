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
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer accountBalance;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private Integer phoneNumber;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Car> cars;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_parking",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "parking_id"))
    private List<Parking> parkings;
}
