package org.abb_tech.task16Endpoint.service;

import org.abb_tech.task16Endpoint.dto.CarDto;

import java.util.List;

public interface CarService {

    List<CarDto> getCars();

    CarDto getCarById(int id);

    void addCar(CarDto car);

    void deleteCarById(int id);

    void updateCar(int id, CarDto carDto);
}
