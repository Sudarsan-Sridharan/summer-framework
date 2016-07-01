package com.mikeldpl.summer.core.injector;

import com.mikeldpl.summer.core.utils.ReflectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

class SimpleBean implements DebtBean<Class<?>> {

    Map<Class<?>, Field> fieldMap = new HashMap<>();
    private DebtBeanHolder<Class<?>, SimpleBean> holder;
    private Class<?> clazz;
    private Object bean;

    public SimpleBean(DebtBeanHolder<Class<?>, SimpleBean> holder, Class<?> clazz) {
        this.holder = holder;
        this.clazz = clazz;
        init();
        fillField();
    }

    private void fillField() {
        if (isInstantiated() && !isAllFilled()) {
            for (Map.Entry<Class<?>, Field> classFieldEntry : fieldMap.entrySet()) {
                Object fieldValue = holder.getNeeded(classFieldEntry.getKey(), this);
                if (fieldValue != null) {
                    setField(classFieldEntry.getValue(), fieldValue);
                }
            }
        }
    }

    void init() {
        try {
            bean = clazz.getConstructor().newInstance();
            holder.put(bean);
            for (Field field : clazz.getDeclaredFields()) {
                fieldMap.put(field.getType(), field);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
//            for (Constructor<?> constructor : clazz.getConstructors()) {
//                constructor.getParameterTypes();
//                System.out.println(constructor.getParameterTypes()[0].getTypeName());
//            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void repay(Class<?> key, Object bean) {
        setField(fieldMap.get(key), bean);
    }

    private void setField(Field field, Object value) {
        ReflectionUtil.setField(this.bean, field, value);
    }

    @Override
    public boolean isInstantiated() {
        return bean != null;
    }

    @Override
    public Object getBeanInstance() {
        return bean;
    }

    @Override
    public Class<?> getBeanClass() {
        return clazz;
    }

    @Override
    public boolean isAllFilled() {
        return fieldMap.isEmpty();
    }
}
