package org.abb_tech.task15Servlet.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.abb_tech.task15Servlet.classes.Teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TeacherServlet",urlPatterns = "/teachers",initParams = {
        @WebInitParam(name = "minExperience",value = "10"),
        @WebInitParam(name = "minSalary",value = "750")
})
public class TeacherServlet extends HttpServlet {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    // for example
    private final List<Teacher> teachers = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.getWriter().println(OBJECT_MAPPER.writeValueAsString(this.teachers));
    }

    @Override
    public void init() throws ServletException {
        ServletContext sc = getServletContext();
        sc.setAttribute("msgFromTeacherServlet","Hello ! from the TeacherServlet .");
        var msgFromStudentServlet = sc.getAttribute("msgFromStudentServlet");
        System.out.println("This is the message - listener's context attribute set by StudentServlet : " + msgFromStudentServlet);

        var p1 = getInitParameter("minExperience");
        var p2 = getInitParameter("minSalary");

        var t1 = new Teacher("Seid",Double.parseDouble(p2),Integer.parseInt(p1));
        var t2 = new Teacher("Cavidan",2500,25);
        this.teachers.add(t1);
        this.teachers.add(t2);

        System.out.println("TeacherServlet initialized");
    }

    @Override
    public void destroy() {
        teachers.clear();
        System.out.println("TeacherServlet destroyed");
    }
}
