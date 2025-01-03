package com.unfi.codechallenges.cars.service;

import com.unfi.codechallenges.cars.dto.CarDto;
import com.unfi.codechallenges.cars.entity.Car;
import com.unfi.codechallenges.cars.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarDto createCar(CarDto car) {
        Car newCar = new Car(car.getMake(), car.getModel(), car.getYear());
        log.info("Creating car");
        Car createdCar = carRepository.save(newCar);
        log.info("Created car with id: {}", createdCar.getId());
        return CarDto.builder()
                .id(createdCar.getId())
                .make(createdCar.getMake())
                .model(createdCar.getModel())
                .year(createdCar.getYear())
                .vin(createdCar.getVin())
                .build();
    }

    public CarDto update(CarDto car) {
        Optional<Car> optionalCar = carRepository.findById(car.getId());
        if (optionalCar.isPresent()) {
            Car foundCar = optionalCar.get();
            foundCar.setMake(car.getModel());
            foundCar.setModel(car.getModel());
            foundCar.setYear(car.getYear());
            foundCar.setVin(car.getVin());
            foundCar.setIsActive(true);
            Car updatedCar = carRepository.save(foundCar);
            return CarDto.builder()
                    .id(updatedCar.getId())
                    .make(updatedCar.getMake())
                    .model(updatedCar.getModel())
                    .year(updatedCar.getYear())
                    .vin(updatedCar.getVin())
                    .build();
        } else {
            throw new RuntimeException("Car not found");
        }
    }

    public void delete(CarDto car) {
        Optional<Car> optionalCar = carRepository.findById(car.getId());
        if (optionalCar.isPresent()) {
            var foundCar = optionalCar.get();
            //log.info("Soft deleting car with id: {}", foundCar.getId());
            foundCar.setIsActive(false);
            carRepository.save(foundCar);
        } else {
            throw new RuntimeException("Car not found");
        }
    }

    public List<CarDto> getAll() {
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
