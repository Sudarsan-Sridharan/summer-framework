package com.mikeldpl.summer.core.context;

import com.mikeldpl.summer.core.injector.SimpleInjector;
import com.mikeldpl.summer.core.scanner.SimpleScanner;

public abstract class SimpleApplicationContext extends BaseContext<SimpleInjector, SimpleScanner> {
    public SimpleApplicationContext() {
        super(new SimpleInjector(), new SimpleScanner());
    }
}
