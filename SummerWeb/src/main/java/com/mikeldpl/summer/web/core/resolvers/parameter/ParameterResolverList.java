package com.mikeldpl.summer.web.core.resolvers.parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Parameter;

public enum ParameterResolverList {
    REQUEST {
        @Override
        public boolean canResolve(Parameter parameter) {
            return parameter.getType().isAssignableFrom(HttpServletRequest.class);
        }

        @Override
        protected ParameterResolver instantiate(Parameter parameter) {
            return new RequestParameterResolver();
        }
    }, RESPONSE {
        @Override
        public boolean canResolve(Parameter parameter) {
            return parameter.getType().isAssignableFrom(HttpServletResponse.class);
        }

        @Override
        protected ParameterResolver instantiate(Parameter parameter) {
            return new ResponseParameterResolver();
        }
    }, SESSION {
        @Override
        public boolean canResolve(Parameter parameter) {
            return parameter.getType().isAssignableFrom(HttpSession.class);
        }

        @Override
        protected ParameterResolver instantiate(Parameter parameter) {
            return new SessionParameterResolver();
        }
    };

    public abstract boolean canResolve(Parameter parameter);

    protected abstract ParameterResolver instantiate(Parameter parameter);

    public ParameterResolver getResolver(Parameter parameter) {
        return canResolve(parameter) ? instantiate(parameter) : null;
    }
}
