package com.mikeldpl.summer.web.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RequestExecutor {
    void exec(HttpServletRequest request, HttpServletResponse response);
}
