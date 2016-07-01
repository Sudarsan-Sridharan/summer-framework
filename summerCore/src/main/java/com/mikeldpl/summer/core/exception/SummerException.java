package com.mikeldpl.summer.core.exception;

public class SummerException extends RuntimeException {

    public SummerException() {
    }

    public SummerException(String message) {
        super(message);
    }

    public SummerException(String message, Throwable cause) {
        super(message, cause);
    }

    public SummerException(Throwable cause) {
        super(cause);
    }
}
