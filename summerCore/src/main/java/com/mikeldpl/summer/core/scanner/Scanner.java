package com.mikeldpl.summer.core.scanner;

import com.mikeldpl.summer.core.core.Observable;

public interface Scanner extends Observable<ScannerObserver> {

    void scan(Package aPackage);
}
