package org.abb_tech.task17and19Db.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

@WebFilter(filterName = "AuthCookieFilter", urlPatterns = {"/*"})
public class AuthCookieFilter extends HttpFilter {

    private static final Set<String> PUBLIC_URLS = Set.of("/car-api/auth/login","/abb-module-2-tasks/cars");

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        String requestUri = req.getRequestURI();
//        if (PUBLIC_URLS.contains(requestUri)) {
//            chain.doFilter(req, res);
//        } else {
//            Cookie[] cookies = req.getCookies();
//            boolean isAuth = false;
//            if (cookies == null) {
//                cookies = new Cookie[0];
//            }
//            for (Cookie cookie : cookies) {
//                if (!cookie.getValue().isBlank()
//                        && cookie.getName().equals("user")) {
//                    isAuth = true;
//                    break;
//                }
//            }
//            if (isAuth) {
//                chain.doFilter(req, res);
//                res.setStatus(200);
//            } else {
//                res.setStatus(401);
//            }
//        }


        //comment
        chain.doFilter(req, res);
    }
}
