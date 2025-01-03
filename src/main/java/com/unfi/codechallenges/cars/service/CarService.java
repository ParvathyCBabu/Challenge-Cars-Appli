package com.unfi.codechallenges.cars.service;

import com.unfi.codechallenges.cars.dto.CarDto;
import com.unfi.codechallenges.cars.entity.Car;
import com.unfi.codechallenges.cars.exception.ResourceNotFoundException;
import com.unfi.codechallenges.cars.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Method to create new Car entry to Database.
     * @return The created Car.
     */
    public CarDto createCar(CarDto car) {
        log.info("Creating car");
        Car foundCar = new Car();
        foundCar.setMake(car.getMake());
        foundCar.setModel(car.getModel());
        foundCar.setYear(car.getYear());
        foundCar.setVin(car.getVin());
        Car updatedCar = carRepository.save(foundCar);
        log.info("Created car with id: {}", updatedCar.getId());
        return CarDto.builder()
                .id(updatedCar.getId())
                .make(updatedCar.getMake())
                .model(updatedCar.getModel())
                .year(updatedCar.getYear())
                .vin(updatedCar.getVin())
                .build();
    }

    /**
     * Method to update existing data entry to Database.
     * @return The updated Car.
     */
    public CarDto update(Long id, CarDto car) throws ResourceNotFoundException {
        log.info("Updating car");
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car foundCar = optionalCar.get();
            foundCar.setMake(car.getMake());
            foundCar.setModel(car.getModel());
            foundCar.setYear(car.getYear());
            foundCar.setVin(car.getVin());
            foundCar.setIsActive(true);
            Car updatedCar = carRepository.save(foundCar);
            log.info("Updated car with id: {}", updatedCar.getId());
            return CarDto.builder()
                    .id(updatedCar.getId())
                    .make(updatedCar.getMake())
                    .model(updatedCar.getModel())
                    .year(updatedCar.getYear())
                    .vin(updatedCar.getVin())
                    .build();
        } else {
            throw new ResourceNotFoundException("Car not found");
        }
    }

    /**
     * Method to delete existing data entry from Database.
     */
    public void delete(Long id, CarDto car) throws ResourceNotFoundException {
        log.info("Deleting car");
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car foundCar = optionalCar.get();
            log.info("Soft deleting car with id: {}", foundCar.getId());
            carRepository.delete(foundCar);
        } else {
            throw new ResourceNotFoundException("Car not found");
        }
    }

    /**
     * Method to get List of Cars from Database.
     * @return The list of Cars.
     */
    public List<CarDto> getAll() {
        log.info("Get all cars");
        List<Car> allCars = carRepository.findAllByIsActiveTrue();
        List<CarDto> cars = new ArrayList<>();
        for (Car car : allCars) {
            cars.add(CarDto.builder()
                    .id(car.getId())
                    .make(car.getMake())
                    .model(car.getModel())
                    .year(car.getYear())
                    .vin(car.getVin())
                    .build()
            );
        }
        return cars;
    }
}
