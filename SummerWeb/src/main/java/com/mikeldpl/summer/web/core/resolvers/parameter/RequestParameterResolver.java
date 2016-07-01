package com.mikeldpl.summer.web.core.resolvers.parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestParameterResolver implements ParameterResolver {
    @Override
    public Object resolve(HttpServletRequest request, HttpServletResponse response) {
        return request;
    }
}
