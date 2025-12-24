package org.abb_tech.task17and19Db.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.abb_tech.task17and19Db.dto.LoginDto;
import org.abb_tech.task17and19Db.dto.UserDetailsDto;
import org.abb_tech.task17and19Db.service.AuthService;
import org.abb_tech.task17and19Db.service.AuthServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = "/auth/login")
public class LoginController extends HttpServlet {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private AuthService authService;

    @Override
    public void init() throws ServletException {
        authService = new AuthServiceImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String id = req.getParameter("id");
//        var params = req.getParameterMap();
//        try {
//            if (id != null) {
//                CarDto car = carService.getCarById(Integer.parseInt(id));
//                resp.getWriter().println(OBJECT_MAPPER.writeValueAsString(car));
//            } else {
//                var cars = carService.getCars();
//                resp.getWriter().println(OBJECT_MAPPER.writeValueAsString(cars));
//            }
//            resp.setStatus(200);
//            resp.setContentType("application/json");
//        } catch (Exception exception) {
//            if (exception instanceof CarNotFoundException) {
//                resp.setStatus(404);
//                resp.getWriter().println("Car not found");
//            } else {
//                resp.setStatus(500);
//                resp.getWriter().println("Internal Server Error");
//            }
//
//        }
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
        LoginDto loginDto = OBJECT_MAPPER.readValue(sb.toString(), LoginDto.class);

        var isAuthenticated = authService.authenticate(loginDto);

        // Cookie based auth
//        if (isAuthenticated) {
//            Cookie user = new Cookie("user", loginDto.userName());
//            user.setHttpOnly(true);
//            user.setMaxAge(60 * 60 * 24 * 30);
//            user.setDomain("localhost");
//            Cookie userChart = new Cookie("userChart", "items");
//            userChart.setMaxAge(20);
//            resp.addCookie(user);
//            resp.addCookie(userChart);
//            resp.setStatus(200);
//        } else {
//            resp.setStatus(401);
//        }


        //Session Based Auth
        if (isAuthenticated) {
            HttpSession session = req.getSession(true);
            session.setAttribute("user",
                    new UserDetailsDto("sample@mail.com",
                            "address", "UserFull Name"));

            session.setMaxInactiveInterval(60 * 60 * 24 * 30);

            resp.setStatus(200);
        } else {
            resp.setStatus(401);
        }

    }
}
