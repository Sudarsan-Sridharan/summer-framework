package com.mikeldpl.summer.web.core.resolvers.returnvalue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ObjectReturnValueResolver extends BaseReturnValueResolver {
    @Override
    protected void resolveSafe(Object value, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().write(value.toString());
    }
}
