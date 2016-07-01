package com.mikeldpl.summer.core.injector;

public interface DebtBeanHolder<K, B extends DebtBean<K>>{

    void put(Object value);

    Object getNeeded(K need, B that);
}
