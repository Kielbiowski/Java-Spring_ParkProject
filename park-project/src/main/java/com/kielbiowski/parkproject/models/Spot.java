package com.kielbiowski.parkproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Spot {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "parking_id")
    private Parking parking;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private String size;

    @OneToOne(mappedBy = "spot")
    private Car car;

    @OneToMany(mappedBy = "spot",fetch = FetchType.LAZY)
    private List<Offer> offers;

    @OneToMany(mappedBy = "spot",fetch = FetchType.LAZY)
    private List<Transaction> transactions;
}
