package com.mikeldpl.summer.web.core;

import com.mikeldpl.summer.core.injector.InjectorObserver;
import com.mikeldpl.summer.mvc.DetectUtil;
import com.mikeldpl.summer.mvc.NameUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InjectorBasedPathMapper implements InjectorObserver, PathMapper {

    private static final char SEPARATOR = '/';

    private static final Pattern PATTERN = Pattern.compile("^(/[^/]+/[^/]+)(/.*)?$");

    private Map<String, RequestExecutor> requestExecutorMap = new HashMap<>();

    @Override
    public void newBean(Object bean) {
        Class<?> aClass = bean.getClass();
        if (DetectUtil.isController(aClass)) {
            String controllerName = NameUtil.getControllerName(aClass);
            for (Method method : aClass.getDeclaredMethods()) {
                if (Modifier.isPublic(method.getModifiers())) {
                    requestExecutorMap.put(SEPARATOR + controllerName + SEPARATOR + method.getName(),
                            new SimpleRequestExecutor(bean, method));
                }
            }
        }
    }

    @Override
    public RequestExecutor getRequestExecutor(HttpServletRequest request) {
        Matcher matcher = PATTERN.matcher(request.getServletPath());
        if (matcher.matches()) {
            return requestExecutorMap.get(matcher.group(1));
        }
        return null;
    }
}
