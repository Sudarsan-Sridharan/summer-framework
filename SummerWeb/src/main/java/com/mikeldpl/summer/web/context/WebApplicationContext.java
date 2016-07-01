package com.mikeldpl.summer.web.context;

import com.mikeldpl.summer.core.context.SimpleApplicationContext;
import com.mikeldpl.summer.web.WebConfiguration;
import com.mikeldpl.summer.web.core.InjectorBasedPathMapper;
import com.mikeldpl.summer.web.core.PathMapper;

import javax.servlet.ServletContext;

public class WebApplicationContext extends SimpleApplicationContext {

    private static final String ATTRIBUTE_NAME = "com.mikeldpl.summer.web.context.WebApplicationContext";
    private PathMapper pathMapper;
    private WebConfiguration configuration = new WebConfiguration();

    public WebApplicationContext(ServletContext servletContext) {
        InjectorBasedPathMapper injectorBasedPathMapper = new InjectorBasedPathMapper();
        this.pathMapper = injectorBasedPathMapper;
        getInjector().setObserver(injectorBasedPathMapper);
        servletContext.setAttribute(ATTRIBUTE_NAME, this);
    }

    public static WebApplicationContext getInstanceByWebApplication(ServletContext servletContext) {
        return (WebApplicationContext) servletContext.getAttribute(ATTRIBUTE_NAME);
    }

    public PathMapper getPathMapper() {
        return pathMapper;
    }

    public WebConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(WebConfiguration configuration) {
        this.configuration = configuration;
    }
}
