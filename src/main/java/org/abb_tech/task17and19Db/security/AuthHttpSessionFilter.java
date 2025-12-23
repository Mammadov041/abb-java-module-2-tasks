package org.abb_tech.task17and19Db.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.abb_tech.task17and19Db.dto.UserDetailsDto;

import java.io.IOException;
import java.util.Set;

@WebFilter(filterName = "AuthHttpSessionFilter",urlPatterns = "/*")
public class AuthHttpSessionFilter extends HttpFilter {

    private static final Set<String> PUBLIC_URLS = Set.of("/my-servlet-app/auth/login","/abb-module-2-tasks/cars");

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String requestUri = req.getRequestURI();
        if (PUBLIC_URLS.contains(requestUri)) {
            chain.doFilter(req, res);
        } else {
            HttpSession session = req.getSession();
            boolean isAuth = false;

            UserDetailsDto userDetailsDto = (UserDetailsDto) session.getAttribute("user");

            if (userDetailsDto != null) {
                userDetailsDto.email().equals("sample@mail.com");
                isAuth = true;
            }
            if (isAuth) {
                chain.doFilter(req, res);
                res.setStatus(200);
            } else {
                res.setStatus(401);
            }
        }
    }
}
