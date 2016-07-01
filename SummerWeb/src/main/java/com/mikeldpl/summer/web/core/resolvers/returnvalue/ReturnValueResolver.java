package com.mikeldpl.summer.web.core.resolvers.returnvalue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ReturnValueResolver {
    void resolve(Object value, HttpServletRequest request, HttpServletResponse response);
}
