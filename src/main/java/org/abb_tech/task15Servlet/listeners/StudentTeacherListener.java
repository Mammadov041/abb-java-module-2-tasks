package org.abb_tech.task15Servlet.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class StudentTeacherListener implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(StudentTeacherListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.log(Level.INFO,StudentTeacherListener.class.getName() + " initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.log(Level.INFO,StudentTeacherListener.class.getName() + " destroyed");
    }
}
