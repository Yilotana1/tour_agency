package com.example.touragency.controller.filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocalizationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String localeS = (String) request.getSession().getAttribute("locale");
        String localeR = request.getParameter("locale");
        if (localeR != null) {
            request.getSession().setAttribute("locale", localeR);
        } else {
            if (localeS == null) {
                request.getSession().setAttribute("locale", "en");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
