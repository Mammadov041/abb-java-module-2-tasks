package org.abb_tech.task17and19Db.repository;

import org.abb_tech.task17and19Db.config.DBConfig;
import org.abb_tech.task17and19Db.exception.CarNotFoundException;
import org.abb_tech.task17and19Db.exception.DBConnectionException;
import org.abb_tech.task17and19Db.model.Car;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDBRepositoryImpl implements CarRepository {
    // private final Connection connection = DBConfig.getConnection();

    @Override
    public List<Car> getCars() {
        String query = "SELECT * FROM public.cars";

        List<Car> cars = new ArrayList<>();

        try (Connection c = DBConfig.getConnection();
             PreparedStatement st = c.prepareStatement(query);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                cars.add(new Car(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("color"),
                        rs.getInt("speed")
                ));
            }

        } catch (SQLException e) {
            throw new DBConnectionException("Could not find cars from db", e);
        }

        return cars;
    }



    @Override
    public Optional<Car> getCarById(int id) {
        String query = "SELECT * FROM cars WHERE id = ?";
        Connection connection = DBConfig.getConnection();
        Car car = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int carId = rs.getInt("id");
                String name = rs.getString("name");
                String color = rs.getString("color");
                int speed = rs.getInt("speed");
                car = new Car(carId,name,color, speed );
            }
        } catch (SQLException exception) {
            throw new DBConnectionException("Could not find car", exception);
        }

        return Optional.ofNullable(car);
    }

    @Override
    public void saveCar(Car car) {
        String query = "INSERT INTO cars (name, color, speed) VALUES (?, ?, ?)";
        Connection connection = DBConfig.getConnection();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, car.getName());
            st.setString(2, car.getColor());
            st.setInt(3, car.getSpeed());
            st.executeUpdate();
        } catch (SQLException exception) {
            throw new DBConnectionException("Could not save car", exception);
        }
    }


    @Override
    public void deleteCarById(int id) {
        String query = "DELETE FROM public.cars WHERE id = ?";
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {

            st.setInt(1, id);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new DBConnectionException("No car found with id " + id);
            }

        } catch (SQLException e) {
            throw new DBConnectionException("Could not delete car", e);
        }
    }


    @Override
    public void updateCar(int id, Car carDto) {
        String sql = """
        UPDATE cars
        SET name = ?, color = ?, speed = ?
        WHERE id = ?
        """;

        try (Connection connection = DBConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, carDto.getName());
            ps.setString(2, carDto.getColor());
            ps.setInt(3, carDto.getSpeed());
            ps.setInt(4, id);

            int updatedRows = ps.executeUpdate();

            if (updatedRows == 0) {
                throw new CarNotFoundException("Car not found with id " + id);
            }
        } catch (SQLException e) {
            throw new DBConnectionException("connection error",e);
        }

    }
}
