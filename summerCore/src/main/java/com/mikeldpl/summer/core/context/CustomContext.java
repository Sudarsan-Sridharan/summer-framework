package com.mikeldpl.summer.core.context;

public interface CustomContext extends Context {

    void startScan();

    void scan(Package aPackage);

    void finishScan();
}
