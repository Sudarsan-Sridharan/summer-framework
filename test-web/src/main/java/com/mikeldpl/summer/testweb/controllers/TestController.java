package com.mikeldpl.summer.testweb.controllers;

import com.mikeldpl.summer.testweb.services.SomeService;

import javax.servlet.ServletRequest;

public class TestController {

    private SomeService someService;

    public String value(ServletRequest request) {
        System.out.println(request);
        return someService.getHello();
    }
}
