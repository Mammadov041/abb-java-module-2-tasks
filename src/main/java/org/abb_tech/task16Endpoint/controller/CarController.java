package org.abb_tech.task16Endpoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.abb_tech.task16Endpoint.dto.CarDto;
import org.abb_tech.task16Endpoint.exception.CarNotFoundException;
import org.abb_tech.task16Endpoint.repository.CarRepositoryImpl;
import org.abb_tech.task16Endpoint.service.CarService;
import org.abb_tech.task16Endpoint.service.CarServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "CarController2", urlPatterns = "/cars2")
public class CarController extends HttpServlet {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private CarService carService;

    @Override
    public void init() throws ServletException {
        carService = new CarServiceImpl(new CarRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        var params = req.getParameterMap();
        try {
            if (id != null) {
                CarDto car = carService.getCarById(Integer.parseInt(id));
                resp.getWriter().println(OBJECT_MAPPER.writeValueAsString(car));
            } else {
                var cars = carService.getCars();
                resp.getWriter().println(OBJECT_MAPPER.writeValueAsString(cars));
            }
            resp.setStatus(200);
            resp.setContentType("application/json");
        } catch (Exception exception) {
            if (exception instanceof CarNotFoundException) {
                resp.setStatus(404);
                resp.getWriter().println("Car not found");
            } else {
                resp.setStatus(500);
                resp.getWriter().println("Internal Server Error");
            }

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
        }
        CarDto CarDto = OBJECT_MAPPER.readValue(sb.toString(), CarDto.class);
        carService.addCar(CarDto);
        resp.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("id");
        try{
            carService.deleteCarById(Integer.parseInt(id));
            resp.setStatus(204);
        }
        catch (Exception exception){
            if(exception instanceof CarNotFoundException){
                resp.setStatus(404);
                resp.getWriter().write("Car Not Found");
            }
            else
                resp.setStatus(500);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer sb = new StringBuffer();
        var id = req.getParameter("id");
        try (BufferedReader bufferedReader = req.getReader()) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        }
        catch (Exception exception){
            if(exception instanceof CarNotFoundException){
                resp.setStatus(404);
                resp.getWriter().write("Car not found");
            }
            else
                resp.setStatus(500);
        }
        CarDto carDto = OBJECT_MAPPER.readValue(sb.toString(), CarDto.class);
        carService.updateCar(Integer.parseInt(id),carDto);
        resp.setStatus(200);
    }
}
