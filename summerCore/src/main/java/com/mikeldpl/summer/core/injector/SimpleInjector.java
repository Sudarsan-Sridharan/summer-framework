package com.mikeldpl.summer.core.injector;

import com.mikeldpl.summer.core.core.ClassFilter;

import java.util.*;

public class SimpleInjector implements Injector, DebtBeanHolder<Class<?>, SimpleBean> {

    private ClassFilter filter;
    private Map<Class<?>, Object> beansMap = new HashMap<>();
    private Map<Class<?>, List<SimpleBean>> debts;
    private InjectorObserver injectorObserver;

    public SimpleInjector() {
        start();
    }

    public void setInjectorFilter(ClassFilter filter) {
        this.filter = filter;
    }

    @Override
    public void meetsClass(Class<?> clazz) {
        if (filter == null || filter.filter(clazz)) {
            newBean(clazz);
        }
    }

    @Override
    public void newBean(Class<?> clazz) {
        new SimpleBean(this, clazz);
    }

    @Override
    public void finish() {
        debts = null;
    }

    @Override
    public void start() {
        if (debts == null) {
            debts = new HashMap<>();
        }
    }

    @Override
    public <T> T get(Class<T> tClass) {
        return (T) beansMap.get(tClass);
    }

    @Override
    public Collection<?> getAll() {
        return beansMap.values();
    }

    @Override
    public void put(Object value) {
        Class<?> key = value.getClass();
        beansMap.put(key, value);
        List<SimpleBean> simpleBeen = debts.get(key);
        if (simpleBeen != null) {
            for (SimpleBean simpleBean : simpleBeen) {
                simpleBean.repay(key, value);
            }
        }
        if (injectorObserver != null) {
            injectorObserver.newBean(value);
        }
    }

    @Override
    public Object getNeeded(Class<?> need, SimpleBean that) {
        Object bean = get(need);
        if (bean == null) {
            List<SimpleBean> simpleBeen = debts.get(need);
            if (simpleBeen == null) {
                simpleBeen = new ArrayList<>();
                debts.put(need, simpleBeen);
            }
            simpleBeen.add(that);
        }
        return bean;
    }

    @Override
    public void setObserver(InjectorObserver o) {
        this.injectorObserver = o;
    }
}
