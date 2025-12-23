package org.abb_tech.task17and19Db.service;

import org.abb_tech.task17and19Db.dto.LoginDto;

public class AuthServiceImpl implements AuthService {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";


    @Override
    public boolean authenticate(LoginDto loginDto) {

        if (USERNAME.equals(loginDto.userName()) && PASSWORD.equals(loginDto.password())) {
            return true;
        } else {
            return false;
        }
    }
}
