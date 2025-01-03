package com.unfi.codechallenges.cars.controller;

import com.unfi.codechallenges.cars.dto.CarDto;
import com.unfi.codechallenges.cars.exception.ResourceNotFoundException;
import com.unfi.codechallenges.cars.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cars")
public class CarsController {

    private final CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    /**
     * Method to get List of Cars from Database.
     * @return The list of Cars.
     */
    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars() {
        log.info("Getting all active cars");
        return ResponseEntity.ok(carService.getAll());
    }

    /**
     * Method to create new Car entry to Database.
     * @return The created Car.
     */
    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto car) {
        log.info("Creating new car");
        return ResponseEntity.ok(carService.createCar(car));
    }

    /**
     * Method to update a data to Database.
     * @return The updated Car.
     */
    @PutMapping()
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto car)  throws ResourceNotFoundException {
        log.info("Updating existing car");
        return ResponseEntity.ok(carService.update(car));
    }

    /**
     * Method to delete a data from Database.
     * @return OK.
     */
    @DeleteMapping()
    public ResponseEntity<CarDto> deleteCar(@RequestBody CarDto car) throws ResourceNotFoundException {
        log.info("Deleting existing car");
        carService.delete(car);
        return ResponseEntity.ok(null);
    }

}