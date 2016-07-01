package com.mikeldpl.summer.web.core;

import javax.servlet.http.HttpServletRequest;

public interface PathMapper {
    RequestExecutor getRequestExecutor(HttpServletRequest request);
}
