package org.abb_tech.task17and19Db.service;

import org.abb_tech.task17and19Db.dto.CarDto;
import org.abb_tech.task17and19Db.exception.CarNotFoundException;
import org.abb_tech.task17and19Db.model.Car;
import org.abb_tech.task17and19Db.repository.CarRepository;

import java.util.List;
import java.util.Optional;

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
                        .name(car.getName())
                        .color(car.getColor())
                        .speed(car.getSpeed())
                        .build())
                .toList();
    }


    @Override
    public Optional<CarDto> getCarById(int id) {
        Car car = carRepository.getCarById(id).
                orElseThrow(() -> new CarNotFoundException("Car not found"));
        return Optional.ofNullable(
                CarDto.builder().speed(car.getSpeed())
                        .color(car.getColor())
                        .name(car.getName())
                        .build()
        );
    }

    @Override
    public void addCar(CarDto car) {
        carRepository.saveCar(new Car(car.getName(), car.getColor(), car.getSpeed()));
    }

    @Override
    public void deleteCarById(int id) {
        carRepository.deleteCarById(id);
    }

    @Override
    public void updateCar(int id, Optional<CarDto> carDto) {
        carDto.ifPresent(dto -> carRepository.updateCar(id, new Car(dto.getName(), dto.getColor(), dto.getSpeed())));
    }
}
