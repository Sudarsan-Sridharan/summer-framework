package com.mikeldpl.summer.web.core.resolvers.returnvalue;

import java.lang.reflect.Method;

public class ReturnValueResolverFactory {

    private ReturnValueResolverFactory() {
    }

    private static class InstanceHolder {
        static final ReturnValueResolverFactory instance = new ReturnValueResolverFactory();
    }

    public static ReturnValueResolverFactory getInstance() {
        return InstanceHolder.instance;
    }

    public ReturnValueResolver createReturnValueResolver(Method method) {
        for (ReturnValueResolverList resolver : ReturnValueResolverList.values()) {
            if (resolver.canResolve(method)) {
                return resolver.getResolver(method);
            }
        }
        return (value, request, response) -> {};
    }
}
