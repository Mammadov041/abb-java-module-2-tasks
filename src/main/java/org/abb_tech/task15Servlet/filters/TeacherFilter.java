package org.abb_tech.task15Servlet.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebFilter(filterName = "TeacherFilter",urlPatterns = "/teachers")
public class TeacherFilter implements Filter {
    private static Logger logger = Logger.getLogger(StudentFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.log(Level.INFO, "TeacherFilter before request");
        chain.doFilter(request, response);
        logger.log(Level.INFO,"TeacherFilter's do filter method worked");
        logger.log(Level.INFO, "TeacherFilter after request");
    }
}
