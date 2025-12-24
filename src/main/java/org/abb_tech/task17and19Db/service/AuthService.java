package org.abb_tech.task17and19Db.service;

import org.abb_tech.task17and19Db.dto.LoginDto;

public interface AuthService {

    boolean authenticate(LoginDto loginDto);
}
