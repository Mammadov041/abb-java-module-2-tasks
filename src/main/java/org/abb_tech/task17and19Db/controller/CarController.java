package org.abb_tech.task17and19Db.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.abb_tech.task17and19Db.dto.CarDto;
import org.abb_tech.task17and19Db.exception.CarNotFoundException;
import org.abb_tech.task17and19Db.repository.CarDBRepositoryImpl;
import org.abb_tech.task17and19Db.service.CarService;
import org.abb_tech.task17and19Db.service.CarServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "CarController", urlPatterns = "/cars")
public class CarController extends HttpServlet {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private CarService carService;

    @Override
    public void init() throws ServletException {
        carService = new CarServiceImpl(new CarDBRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");

        try {
            if (id != null) {
                CarDto car = carService.getCarById(Integer.parseInt(id))
                        .orElseThrow(() -> new CarNotFoundException("Car not found"));
                resp.setStatus(200);
                resp.getWriter().write(OBJECT_MAPPER.writeValueAsString(car));
            } else {
                List<CarDto> cars = carService.getCars(); // now returns List<CarDto>
                resp.setStatus(200);
                resp.getWriter().write(OBJECT_MAPPER.writeValueAsString(cars));
            }
        } catch (CarNotFoundException e) {
            resp.setStatus(404);
            resp.getWriter().write("{\"message\":\"Car not found\"}");
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().write("{\"message\":\"Internal Server Error\"}");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer sb = new StringBuffer();
        try (BufferedReader bufferedReader = req.getReader()) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            CarDto CarDto = OBJECT_MAPPER.readValue(sb.toString(), CarDto.class);
            carService.addCar(CarDto);
        }
        catch (Exception exception){
            resp.setStatus(500);
            resp.getWriter().write("There was server error" + exception);
        }
        resp.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String id = req.getParameter("id");

        if(id != null){
            try {
                carService.getCarById(Integer.parseInt(id));
                carService.deleteCarById(Integer.parseInt(id));
                resp.setStatus(204);
            }catch (Exception exception){
                resp.setStatus(404);
                resp.getWriter().write("Car not found");
            }
        }else{
            resp.setStatus(400);
            resp.getWriter().write("Can not delete car without id field");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        var id = req.getParameter("id");
        StringBuffer sb = new StringBuffer();
        try (BufferedReader bufferedReader = req.getReader()) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            CarDto updatedCar = OBJECT_MAPPER.readValue(sb.toString(), CarDto.class);
            var car = carService.getCarById(Integer.parseInt(id));
            if (car.isPresent() && updatedCar != null && id != null) {
                carService.updateCar(Integer.parseInt(id),Optional.of(updatedCar));
                resp.setStatus(200);
            } else {
                resp.setStatus(404);
                resp.getWriter().write("There is no car with id " + id);
            }
        }
        catch (Exception exception) {
            resp.setStatus(500);
        }
    }
}
