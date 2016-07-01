package com.mikeldpl.summer.web.core.resolvers.returnvalue;

import com.mikeldpl.summer.web.exception.ConfigurationWebSummerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseReturnValueResolver implements ReturnValueResolver {
    @Override
    public void resolve(Object value, HttpServletRequest request, HttpServletResponse response) {
        if (!response.isCommitted()) {
            try {
                resolveSafe(value, request, response);
                response.flushBuffer();
            } catch (Exception e) {
                throw new ConfigurationWebSummerException(e);
            }
        }
    }

    protected abstract void resolveSafe(Object value, HttpServletRequest request, HttpServletResponse response)
            throws Exception;
}
