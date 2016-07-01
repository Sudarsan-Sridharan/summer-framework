package com.mikeldpl.summer.web.core.resolvers.returnvalue;

import java.lang.reflect.Method;

public enum ReturnValueResolverList {
    STRING {
        @Override
        public boolean canResolve(Method method) {
            return method.getReturnType() == String.class;
        }

        @Override
        protected ReturnValueResolver instantiate(Method method) {
            return new StringReturnValueResolver();
        }
    }, OBJECT {
        @Override
        public boolean canResolve(Method method) {
            return method.getReturnType() != void.class;
        }

        @Override
        protected ReturnValueResolver instantiate(Method method) {
            return null;
        }
    };


    public abstract boolean canResolve(Method method);

    protected abstract ReturnValueResolver instantiate(Method method);

    public ReturnValueResolver getResolver(Method method) {
        return canResolve(method) ? instantiate(method) : null;
    }
}
