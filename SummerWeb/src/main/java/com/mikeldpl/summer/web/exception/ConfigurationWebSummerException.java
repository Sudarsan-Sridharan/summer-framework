package com.mikeldpl.summer.web.exception;

public class ConfigurationWebSummerException extends WebSummerException{
    public ConfigurationWebSummerException() {
    }

    public ConfigurationWebSummerException(String message) {
        super(message);
    }

    public ConfigurationWebSummerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigurationWebSummerException(Throwable cause) {
        super(cause);
    }
}
