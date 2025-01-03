package com.unfi.codechallenges.cars.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import com.unfi.codechallenges.cars.dto.CarDto;
import com.unfi.codechallenges.cars.entity.Car;
import com.unfi.codechallenges.cars.exception.ResourceNotFoundException;
import com.unfi.codechallenges.cars.repository.CarRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Mock
    private CarDto carDto;

    private Car getListOfCars() {
        List<Car> carList = new ArrayList<>();
        Car car = new Car();
        car.setId(100L);
        car.setMake("Toyota");
        car.setModel("Limosin Plus");
        car.setYear("1990");
        car.setVin("Vin123456789");
        car.setIsActive(Boolean.TRUE);
        car.setCreatedAt(LocalDateTime.parse("2025-01-03T09:53:45.214681"));
        car.setLastUpdated(LocalDateTime.parse("2025-01-03T09:53:45.214681"));
        return car;
    }

    @SneakyThrows
    @Test
    public void testProcessUser() {
        CarDto car = CarDto.builder().make("Audi").model("S4").year("2020").vin("ASEDF908U9F983HA").build();
        when(carRepository.save(any(Car.class))).thenReturn(new Car());
            carService.createCar(car);
    }

    @SneakyThrows
    @Test
    public void testUpdateNeg()  {
        CarDto carDto = CarDto.builder().id(1L).make("Audi").model("S4").year("2020").vin("ASEDF908U9F983HA").build();
        Car car = getListOfCars();
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        when(carRepository.findById(10L)).thenReturn(Optional.of(carList.get(0)));
        when(carRepository.save(car)).thenReturn(car);
        assertThrows(ResourceNotFoundException.class, () -> {
            carService.update(1l, carDto);
        });
    }

    @SneakyThrows
    @Test
    public void testUpdatePositiveScenario(){
        CarDto carDto = CarDto.builder().id(100L).make("Toyota").model("Limosin Plus").year("1990").vin("Vin123456789").build();
        Car car = getListOfCars();
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        when(carRepository.findById(100L)).thenReturn(Optional.of(carList.get(0)));
        when(carRepository.save(car)).thenReturn(car);
        CarDto userDtoList= carService.update(100L, carDto);
        assertEquals(car.getId(), carDto.getId());
        assertEquals(car.getMake(), carDto.getMake());
        assertEquals(car.getModel(), carDto.getModel());
        assertEquals(car.getYear(), carDto.getYear());
        assertEquals(car.getVin(), carDto.getVin());
    }

    @SneakyThrows
    @Test
    public void testDeleteNeg()  {
        CarDto carDto = CarDto.builder().id(1L).make("Audi").model("S4").year("2020").vin("ASEDF908U9F983HA").build();
        Car car = getListOfCars();
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        when(carRepository.findById(10L)).thenReturn(Optional.of(carList.get(0)));
        when(carRepository.save(car)).thenReturn(car);
        assertThrows(ResourceNotFoundException.class, () -> {
            carService.delete(1L, carDto);
        });
    }

    @SneakyThrows
    @Test
    public void testDeletePositiveScenario(){
        CarDto carDto = CarDto.builder().id(100L).make("Toyota").model("Limosin Plus").year("1990").vin("Vin123456789").build();
        Car car = getListOfCars();
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        when(carRepository.findById(100L)).thenReturn(Optional.of(carList.get(0)));
        when(carRepository.save(car)).thenReturn(car);
        carService.delete(100L, carDto);
        assertEquals(car.getId(), carDto.getId());
        assertEquals(car.getMake(), carDto.getMake());
        assertEquals(car.getModel(), carDto.getModel());
        assertEquals(car.getYear(), carDto.getYear());
        assertEquals(car.getVin(), carDto.getVin());
    }

    @Test
    public void testGetAllScenario(){
        Car car = getListOfCars();
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        when(carRepository.findAllByIsActiveTrue()).thenReturn(carList);
        carService.getAll();
    }
}
