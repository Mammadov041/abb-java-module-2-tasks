package org.abb_tech.task16Endpoint.repository;

import org.abb_tech.task16Endpoint.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {

    List<Car> getCars();

    Optional<Car> getCarById(int id);

    void saveCar(Car car);

    void deleteCarById(int id);

    void updateCar(int id, Car carDto);

}
