package com.mikeldpl.summer.web.servlet;

import com.mikeldpl.summer.web.Constants;
import com.mikeldpl.summer.web.WebConfiguration;
import com.mikeldpl.summer.web.context.WebApplicationContext;
import com.mikeldpl.summer.web.core.PathMapper;
import com.mikeldpl.summer.web.core.RequestExecutor;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "IrresistibleServlet", value = "/", loadOnStartup = 0)
public class IrresistibleServlet implements Servlet {

    private PathMapper pathMapper;
    private ServletConfig config;
    private WebConfiguration webConfiguration;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        WebApplicationContext instanceByWebApplication = WebApplicationContext
                .getInstanceByWebApplication(config.getServletContext());
        this.pathMapper = instanceByWebApplication.getPathMapper();
        this.webConfiguration = instanceByWebApplication.getConfiguration();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) res;
        RequestExecutor requestExecutor = pathMapper.getRequestExecutor(httpRequest);
        if (requestExecutor == null) {
            if (webConfiguration.forwardToResources
                    || req.getAttribute(Constants.SHOW_VIEW_ATTRIBUTE) != null) {
                config.getServletContext().getNamedDispatcher("default").forward(req, res);
            } else {
                httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            requestExecutor.exec(httpRequest, httpResponse);
        }
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public String getServletInfo() {
        return "General servlet for summer framework";
    }

    @Override
    public void destroy() {
    }
}
