package org.abb_tech.task17and19Db.service;

import org.abb_tech.task17and19Db.dto.CarDto;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<CarDto> getCars();

    Optional<CarDto> getCarById(int id);

    void addCar(CarDto car);

    void deleteCarById(int id);

    void updateCar(int id, Optional<CarDto> carDto);
}
