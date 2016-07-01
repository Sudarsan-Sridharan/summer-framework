package com.mikeldpl.summer.core.utils;

import java.lang.reflect.Field;

public class ReflectionUtil {

    private ReflectionUtil() {
    }

    public static void setField(Object object, Field field, Object value) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
