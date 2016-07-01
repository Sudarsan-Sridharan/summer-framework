package com.mikeldpl.summer.web.core.resolvers.parameter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ParameterResolver {
    Object resolve(HttpServletRequest request, HttpServletResponse response);
}
