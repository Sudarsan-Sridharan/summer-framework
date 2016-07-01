package com.mikeldpl.summer.web.core.resolvers.parameter;

import java.lang.reflect.Parameter;

public class ParameterResolverFactory {

    private ParameterResolverFactory() {
    }

    private static class InstanceHolder {
        static final ParameterResolverFactory instance = new ParameterResolverFactory();
    }

    public static ParameterResolverFactory getInstance() {
        return InstanceHolder.instance;
    }

    public ParameterResolver createParameterResolver(Parameter parameter) {
        for (ParameterResolverList resolver : ParameterResolverList.values()) {
            if (resolver.canResolve(parameter)) {
                return resolver.getResolver(parameter);
            }
        }
        return (request, response) -> null;
    }
}
