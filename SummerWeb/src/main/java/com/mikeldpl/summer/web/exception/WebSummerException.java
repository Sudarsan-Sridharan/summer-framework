package com.mikeldpl.summer.web.exception;

import com.mikeldpl.summer.core.exception.SummerException;

public class WebSummerException extends SummerException {
    public WebSummerException() {
    }

    public WebSummerException(String message) {
        super(message);
    }

    public WebSummerException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebSummerException(Throwable cause) {
        super(cause);
    }
}
