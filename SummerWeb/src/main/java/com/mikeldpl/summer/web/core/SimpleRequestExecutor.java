package com.mikeldpl.summer.web.core;

import com.mikeldpl.summer.web.core.resolvers.parameter.ParameterResolver;
import com.mikeldpl.summer.web.core.resolvers.parameter.ParameterResolverFactory;
import com.mikeldpl.summer.web.core.resolvers.returnvalue.ReturnValueResolver;
import com.mikeldpl.summer.web.core.resolvers.returnvalue.ReturnValueResolverFactory;
import com.mikeldpl.summer.web.exception.InvokeWebSummerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class SimpleRequestExecutor implements RequestExecutor {

    private Object bean;
    private Method method;
    private int parameterCount;
    private ParameterResolver[] parameterResolvers;
    private ReturnValueResolver valueResolver;


    public SimpleRequestExecutor(Object bean, Method method) {
        this.bean = bean;
        this.method = method;
        this.parameterCount = method.getParameterCount();
        valueResolver = ReturnValueResolverFactory.getInstance().createReturnValueResolver(method);
        initParameterResolvers();
    }

    private void initParameterResolvers() {
        Parameter[] parameters = method.getParameters();
        assert parameters.length == parameterCount;
        ParameterResolverFactory factory = ParameterResolverFactory.getInstance();
        this.parameterResolvers = new ParameterResolver[parameterCount];
        for (int i = 0; i < parameterResolvers.length; i++) {
            parameterResolvers[i] = factory.createParameterResolver(parameters[i]);
        }
    }

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            Object[] parameters = new Object[parameterCount];
            for (int i = 0; i < parameterCount; i++) {
                parameters[i] = parameterResolvers[i].resolve(request, response);
            }
            valueResolver.resolve(method.invoke(bean, parameters), request, response);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new InvokeWebSummerException(e);
        }
    }
}
