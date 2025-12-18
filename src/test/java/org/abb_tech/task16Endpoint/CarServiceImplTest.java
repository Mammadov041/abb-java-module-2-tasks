package org.abb_tech.task16Endpoint;

import org.abb_tech.task16Endpoint.dto.CarDto;
import org.abb_tech.task16Endpoint.exception.CarNotFoundException;
import org.abb_tech.task16Endpoint.model.Car;
import org.abb_tech.task16Endpoint.repository.CarRepository;
import org.abb_tech.task16Endpoint.service.CarServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;


    @Test
    void getCarById_success() {
        Mockito.when(carRepository.getCarById(1))
                .thenReturn(Optional.of(new Car("Red", 100)));
        CarDto actual = carService.getCarById(1);
        CarDto expected = new CarDto("Red", 100);
        Assertions.assertEquals(expected.getColor(), actual.getColor());
        Assertions.assertEquals(expected.getSpeed(), actual.getSpeed());
    }

    @Test
    void getCarById_throw() {
        Mockito.when(carRepository.getCarById(1))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(CarNotFoundException.class, () -> carService.getCarById(1));
    }

}
