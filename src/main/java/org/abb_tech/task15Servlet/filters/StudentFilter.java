package org.abb_tech.task15Servlet.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebFilter(filterName = "StudentFilter",urlPatterns = "/students")
public class StudentFilter implements Filter {
    private static Logger logger = Logger.getLogger(StudentFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.log(Level.INFO, "StudentFilter before request");
        chain.doFilter(request, response);
        logger.log(Level.INFO,"StudentFilter's do filter method worked");
        logger.log(Level.INFO, "StudentFilter after request");
    }
}
