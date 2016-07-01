package com.mikeldpl.summer.testweb;

import com.mikeldpl.summer.testweb.services.StartService;
import com.mikeldpl.summer.web.StartPoint;

public class Starter implements StartPoint {

    private StartService startService;

    @Override
    public void onStart() {
        System.out.println(startService.getStarterMessage());
    }
}
