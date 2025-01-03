package com.unfi.codechallenges.cars.repository;

import com.unfi.codechallenges.cars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    public List<Car> findAllByIsActiveTrue();
}
