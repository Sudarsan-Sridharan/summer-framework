package com.mikeldpl.summer.mvc;

public class DetectUtil {

    private DetectUtil() {
    }

    public static boolean isController(Class<?> clazz) {
        return isEndsNameWith(clazz, Constants.CONTROLLER_SUFFIX);
    }

    public static boolean isService(Class<?> clazz) {
        return isEndsNameWith(clazz, Constants.SERVICE_SUFFIX);
    }

    private static boolean isEndsNameWith(Class<?> clazz, String suffix) {
        return clazz.getSimpleName().endsWith(suffix);
    }
}
