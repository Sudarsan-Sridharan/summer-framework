package com.mikeldpl.summer.web.core.resolvers.parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseParameterResolver implements ParameterResolver{
    @Override
    public Object resolve(HttpServletRequest request, HttpServletResponse response) {
        return response;
    }
}
