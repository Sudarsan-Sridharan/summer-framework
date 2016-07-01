package com.mikeldpl.summer.web.core.resolvers.returnvalue;

import com.mikeldpl.summer.web.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StringReturnValueResolver extends BaseReturnValueResolver {

    @Override
    protected void resolveSafe(Object value, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute(Constants.SHOW_VIEW_ATTRIBUTE, Boolean.TRUE);
        request.getRequestDispatcher((String)value)
                .forward(request, response);
    }
}
