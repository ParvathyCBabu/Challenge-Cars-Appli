package com.unfi.codechallenges.cars.controller;

import com.unfi.codechallenges.cars.dto.CarDto;
import com.unfi.codechallenges.cars.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cars")
public class CarsController {

    private final CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars() {
        log.info("Getting all active cars");
        return ResponseEntity.ok(carService.getAll());
    }

    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto car) {
        return ResponseEntity.ok(carService.createCar(car));
    }

    @PostMapping(path = "/update")
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto car) {
        return ResponseEntity.ok(carService.update(car));
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<CarDto> deleteCar(@RequestBody CarDto car) {
        carService.delete(car);
        return ResponseEntity.ok(null);
    }

    // Custom exception handler for validation errors
    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<String> handleValidationExceptions(Exception e) {
        log.error("Uh oh, an exception happened.");
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}