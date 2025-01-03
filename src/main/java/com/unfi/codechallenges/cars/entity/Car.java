package com.unfi.codechallenges.cars.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "CAR")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAR_SEQ")
    @SequenceGenerator(sequenceName = "CAR_SEQ", allocationSize = 1, name = "CAR_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "MAKE")
    private String make;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "YEAR_BUILT")
    private String year;

    @Column(name = "VIN")
    private String vin;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Column(updatable = false, name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "LAST_UPDATED")
    private LocalDateTime lastUpdated;

    // Constructors
    public Car() {}

    public Car(String make, String model, String year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        lastUpdated = LocalDateTime.now();
        isActive = true;
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
        isActive = true;
    }
}