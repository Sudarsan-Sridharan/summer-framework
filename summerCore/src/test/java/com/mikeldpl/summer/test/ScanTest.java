package com.mikeldpl.summer.test;

import com.mikeldpl.summer.core.context.ApplicationContext;
import org.junit.Test;

public class ScanTest {

    @Test
    public void testScan(){
        ApplicationContext applicationContext = new ApplicationContext();
        System.out.println(applicationContext.getAllBeans());
//        Class<Class2> class2Class = Class2.class;
//        System.out.println(class2Class.getConstructors()[0].getParameterTypes()[0]);

    }
}
