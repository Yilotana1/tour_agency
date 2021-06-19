package com.example.touragency.controller.filters;

import com.example.touragency.model.entity.enums.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.touragency.model.entity.enums.Role.*;

public class AccessFilter implements Filter {

    private static final Map<Role, List<String>> accessMap = new HashMap<>();

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        accessMap.put(UNKNOWN,
                Arrays.asList(filterConfig.getServletContext().getContextPath() + "/login",
                        filterConfig.getServletContext().getContextPath() + "/login.jsp",
                        filterConfig.getServletContext().getContextPath() + "/main.jsp",
                        filterConfig.getServletContext().getContextPath() + "/",
                        filterConfig.getServletContext().getContextPath() + "/error.jsp",
                        filterConfig.getServletContext().getContextPath() + "/register.jsp",
                        filterConfig.getServletContext().getContextPath() + "/register"));

        accessMap.put(CLIENT,
                Arrays.asList(filterConfig.getServletContext().getContextPath() + "/logout",
                        filterConfig.getServletContext().getContextPath() + "/main.jsp",
                        filterConfig.getServletContext().getContextPath() + "/client/client_page.jsp",
                        filterConfig.getServletContext().getContextPath() + "/error.jsp"));

        accessMap.put(MANAGER,
                Arrays.asList(filterConfig.getServletContext().getContextPath() + "/logout",
                        filterConfig.getServletContext().getContextPath() + "/main.jsp",
                        filterConfig.getServletContext().getContextPath() + "/manager/manager_page.jsp",
                        filterConfig.getServletContext().getContextPath() + "/error.jsp"));

        accessMap.put(ADMIN,
                Arrays.asList(filterConfig.getServletContext().getContextPath() + "/logout",
                        filterConfig.getServletContext().getContextPath() + "/main.jsp",
                        filterConfig.getServletContext().getContextPath() + "/admin/admin_page.jsp",
                        filterConfig.getServletContext().getContextPath() + "/error.jsp"));
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        Role userRole = (Role) httpRequest.getSession().getAttribute("role");

        if (userRole == null){
            httpRequest.getSession().setAttribute("role", UNKNOWN);
            userRole = UNKNOWN;
        }

        httpRequest.getSession().setAttribute("PATH", filterConfig.getServletContext().getContextPath());

        if (  !(accessMap.get(userRole).contains(httpRequest.getRequestURI()))  ) {
            httpResponse.sendRedirect(filterConfig.getServletContext().getContextPath() + "/error.jsp");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
