package org.abb_tech.task17and19Db.exception;

import java.io.Serial;

public class CarNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public CarNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarNotFoundException(String message) {
        super(message);
    }
}
