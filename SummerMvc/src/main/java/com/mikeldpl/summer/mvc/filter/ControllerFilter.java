package com.mikeldpl.summer.mvc.filter;

import com.mikeldpl.summer.core.core.ClassFilter;
import com.mikeldpl.summer.mvc.DetectUtil;

public class ControllerFilter implements ClassFilter {
    @Override
    public boolean filter(Class<?> object) {
        return DetectUtil.isController(object);
    }
}
