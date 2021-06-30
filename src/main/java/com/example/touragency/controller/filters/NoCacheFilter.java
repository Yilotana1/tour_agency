package com.example.touragency.controller.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Reject browser page caching.
 * Uses in order to do not give permission for users to get on page after logout
 */
public class NoCacheFilter implements Filter {
    public final static Logger log = Logger.getLogger(NoCacheFilter.class);


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        log.trace("set header: cache-control");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Filter initialized");
    }

    @Override
    public void destroy() {
        log.debug("Filter destroyed");
    }
}
