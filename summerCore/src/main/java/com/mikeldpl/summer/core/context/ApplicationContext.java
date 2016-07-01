package com.mikeldpl.summer.core.context;

import com.mikeldpl.summer.core.exception.SummerException;

public class ApplicationContext extends SimpleApplicationContext {

    public ApplicationContext() {
        fullScan(getPriviesClass());
    }

    public ApplicationContext(Class<?> aClass) {
        fullScan(aClass);
    }

    private void fullScan(Class<?> aClass) {
        startScan();
        scan(aClass.getPackage());
        finishScan();
    }

    private Class<?> getPriviesClass() {
        try {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (!stackTraceElement.getClassName().equals(ApplicationContext.class.getName()))
                    return Thread.currentThread().getContextClassLoader().loadClass(stackTraceElement.getClassName());
            }
        } catch (ClassNotFoundException e) {
            throw new SummerException(e);
        }
        throw new SummerException();
    }
}
