package org.abb_tech.task15Servlet.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.abb_tech.task15Servlet.classes.Student;
import org.abb_tech.task15Servlet.classes.Teacher;

import javax.swing.text.html.parser.Parser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StudentServlet",urlPatterns = {"/students"},initParams = @WebInitParam(name= "minAge",value = "6"))
public class StudentServlet extends HttpServlet
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    // for example
    private final List<Student> students = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.getWriter().println(OBJECT_MAPPER.writeValueAsString(this.students));

        ServletContext sc = getServletContext();
        var msgFromTeacherServlet = sc.getAttribute("msgFromTeacherServlet");

        //resp.getWriter().write(OBJECT_MAPPER.writeValueAsString(msgFromTeacherServlet));
    }

    @Override
    public void init() throws ServletException {
        ServletContext sc = getServletContext();
        sc.setAttribute("msgFromStudentServlet","Hello ! from the StudentServlet .");

        var p1 = getInitParameter("minAge");

        var s1 = new Student("Emin",82,16);
        var s2 = new Student("Fariz",95, Integer.parseInt(p1));

        this.students.add(s1);
        this.students.add(s2);

        System.out.println("StudentServlet initialized");
    }

    @Override
    public void destroy() {
        students.clear();
        System.out.println("StudentServlet destroyed");
    }
}
