package com.mikeldpl.summer.core.context;

import com.mikeldpl.summer.core.injector.Injector;
import com.mikeldpl.summer.core.scanner.Scanner;

import java.util.Collection;

public abstract class BaseContext<I extends Injector, S extends Scanner> implements CustomContext {
    protected I injector;
    protected S scanner;

    public BaseContext(I injector, S scanner) {
        this.injector = injector;
        this.scanner = scanner;
        scanner.setObserver(injector);
        if (ContextHolder.context == null) {
            ContextHolder.context = this;
        }
        injector.put(this);
    }

    protected I getInjector() {
        return injector;
    }

    protected S getScanner() {
        return scanner;
    }

    public <T> T getBean(Class<T> clazz) {
        return injector.get(clazz);
    }

    public void scan(Package aPackage) {
        scanner.scan(aPackage);
    }

    @Override
    public void startScan() {
        injector.start();
    }

    @Override
    public void finishScan() {
        injector.finish();
    }

    public Collection<?> getAllBeans() {
        return injector.getAll();
    }
}