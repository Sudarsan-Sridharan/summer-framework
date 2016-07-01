package com.mikeldpl.summer.web;

import com.mikeldpl.summer.web.context.WebApplicationContext;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

@HandlesTypes(StartPoint.class)
public class SummerServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        WebApplicationContext applicationContext = new WebApplicationContext(servletContext);
        for (Class<?> aClass : set) {
            applicationContext.scan(aClass.getPackage());
        }
        for (Class<?> aClass : set) {
            ((StartPoint)applicationContext.getBean(aClass)).onStart();
        }
    }
}
