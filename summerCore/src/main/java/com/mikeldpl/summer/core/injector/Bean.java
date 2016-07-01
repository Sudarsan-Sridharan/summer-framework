package com.mikeldpl.summer.core.injector;

public interface Bean {

    Object getBeanInstance();

    Class<?> getBeanClass();

    boolean isAllFilled();

    boolean isInstantiated();
}
