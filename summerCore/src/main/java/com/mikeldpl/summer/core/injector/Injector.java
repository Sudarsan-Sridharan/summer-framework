package com.mikeldpl.summer.core.injector;

import com.mikeldpl.summer.core.core.ClassFilter;
import com.mikeldpl.summer.core.core.Observable;
import com.mikeldpl.summer.core.scanner.ScannerObserver;

import java.util.Collection;

public interface Injector extends ScannerObserver, Observable<InjectorObserver> {

    void setInjectorFilter(ClassFilter filter);

    void newBean(Class<?> clazz);

    <T> void put(T instance);

    <T> T get(Class<T> tClass);

    Collection<?> getAll();

    void finish();

    void start();
}
