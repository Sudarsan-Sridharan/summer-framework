package com.mikeldpl.summer.mvc;

public class NameUtil {

    public static String getControllerName(Class<?> aClass) {
        if (DetectUtil.isController(aClass)) {
            return getNameBySuffix(aClass, Constants.CONTROLLER_SUFFIX);
        }
        return null;
    }

    public static String getServiceName(Class<?> aClass) {
        if (DetectUtil.isService(aClass)) {
            return getNameBySuffix(aClass, Constants.SERVICE_SUFFIX);
        }
        return null;
    }

    private static String getNameBySuffix(Class<?> aClass, String suffix) {
        String simpleName = aClass.getSimpleName();
        return simpleName.substring(0, simpleName.length() - suffix.length());
    }
}
