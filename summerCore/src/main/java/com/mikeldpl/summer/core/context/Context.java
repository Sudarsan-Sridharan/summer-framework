package com.mikeldpl.summer.core.context;

public interface Context {
    <T> T getBean(Class<T> clazz);
}
