package org.abb_tech.task17and19Db.exception;

import java.io.Serial;

public class DBConnectionException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public DBConnectionException(String message) {
        super(message);
    }

    public DBConnectionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
