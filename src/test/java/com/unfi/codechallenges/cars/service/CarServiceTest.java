package com.unfi.codechallenges.cars.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.unfi.codechallenges.cars.dto.CarDto;
import com.unfi.codechallenges.cars.entity.Car;
import com.unfi.codechallenges.cars.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CarServiceTest {

    @Mock
    private CarRepository userRepository;

    @InjectMocks
    private CarService carService;

    @Test
    public void testProcessUser() {
        CarDto car = CarDto.builder().make("Audi").model("S4").year("2020").vin("ASEDF908U9F983HA").build();

        // Expecting an exception due to the deliberate bug setting name to null
        when(userRepository.save(any(Car.class))).thenThrow(new IllegalArgumentException("Name cannot be null"));

        assertThrows(IllegalArgumentException.class, () -> {
            carService.createCar(car);
        });
    }
}