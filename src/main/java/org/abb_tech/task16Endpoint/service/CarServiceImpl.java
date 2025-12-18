package org.abb_tech.task16Endpoint.service;

import org.abb_tech.task16Endpoint.dto.CarDto;
import org.abb_tech.task16Endpoint.exception.CarNotFoundException;
import org.abb_tech.task16Endpoint.model.Car;
import org.abb_tech.task16Endpoint.repository.CarRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<CarDto> getCars() {
        return carRepository.getCars()
                .stream()
                .map(car -> CarDto.builder()
                        .color(car.getColor())
                        .speed(car.getSpeed())
                        .id(car.getId())
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public CarDto getCarById(int id) {
        Car car = carRepository.getCarById(id).
                orElseThrow(() -> new CarNotFoundException("Car not found"));
        return CarDto.builder()
                .color(car.getColor())
                .speed(car.getSpeed())
                .id(car.getId())
                .build();
    }

    @Override
    public void addCar(CarDto car) {
        carRepository.saveCar(new Car(car.getColor(), car.getSpeed()));
    }

    @Override
    public void deleteCarById(int id) {
        carRepository.deleteCarById(id);
    }

    @Override
    public void updateCar(int id, CarDto carDto) {
        carRepository.updateCar(id, new Car(carDto.getColor(), carDto.getSpeed()));
    }
}
