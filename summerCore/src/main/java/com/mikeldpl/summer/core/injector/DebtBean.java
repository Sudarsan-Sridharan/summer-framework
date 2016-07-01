package com.mikeldpl.summer.core.injector;

public interface DebtBean<K> extends Bean {

    void repay(K key, Object bean);
}
