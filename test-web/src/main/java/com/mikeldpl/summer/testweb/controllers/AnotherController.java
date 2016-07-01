package com.mikeldpl.summer.testweb.controllers;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AnotherController {
    public void partPath(HttpServletResponse response) throws IOException {
        response.getWriter().write("It is working!!!!!!!!");
    }
}
