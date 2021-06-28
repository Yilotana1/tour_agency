package com.example.touragency.controller.filters;

import com.example.touragency.constants.Path;
import com.example.touragency.controller.commands.CommandUtility;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.enums.Role;
import org.apache.log4j.Logger;

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

    public final static Logger log = Logger.getLogger(AccessFilter.class);

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig){
        this.filterConfig = filterConfig;
        accessMap.put(UNKNOWN,
                Arrays.asList(filterConfig.getServletContext().getContextPath() + Path.LOGIN,
                        filterConfig.getServletContext().getContextPath() + Path.LOGIN_FORM,
                        filterConfig.getServletContext().getContextPath() + Path.MAIN,
                        filterConfig.getServletContext().getContextPath() + Path.ERROR_404,
                        filterConfig.getServletContext().getContextPath() + Path.ERROR_503,
                        filterConfig.getServletContext().getContextPath() + Path.REGISTER_FORM,
                        filterConfig.getServletContext().getContextPath() + Path.REGISTER,
                        filterConfig.getServletContext().getContextPath() + Path.TOUR_ORDER,
                        filterConfig.getServletContext().getContextPath() + Path.ORDER_FORM));

        accessMap.put(CLIENT,
                Arrays.asList(filterConfig.getServletContext().getContextPath() + Path.LOGOUT,
                        filterConfig.getServletContext().getContextPath() + Path.MAIN,
                        filterConfig.getServletContext().getContextPath() + Path.CLIENT_PAGE,
                        filterConfig.getServletContext().getContextPath() + Path.ERROR_404,
                        filterConfig.getServletContext().getContextPath() + Path.ERROR_503,
                        filterConfig.getServletContext().getContextPath() + Path.TOUR_ORDER,
                        filterConfig.getServletContext().getContextPath() + Path.ORDER_FORM,
                        filterConfig.getServletContext().getContextPath() + Path.MY_ORDERS,
                        filterConfig.getServletContext().getContextPath() + Path.EDIT_PROFILE,
                        filterConfig.getServletContext().getContextPath() + Path.PROFILE_VIEW));

        accessMap.put(MANAGER,
                Arrays.asList(filterConfig.getServletContext().getContextPath() + Path.LOGOUT,
                        filterConfig.getServletContext().getContextPath() + Path.MAIN,
                        filterConfig.getServletContext().getContextPath() + Path.MANAGER_PAGE,
                        filterConfig.getServletContext().getContextPath() + Path.ERROR_404,
                        filterConfig.getServletContext().getContextPath() + Path.ERROR_503,
                        filterConfig.getServletContext().getContextPath() + Path.TOUR_ORDER,
                        filterConfig.getServletContext().getContextPath() + Path.ORDER_FORM,
                        filterConfig.getServletContext().getContextPath() + Path.MY_ORDERS,
                        filterConfig.getServletContext().getContextPath() + Path.EDIT_PROFILE,
                        filterConfig.getServletContext().getContextPath() + Path.PROFILE_VIEW,
                        filterConfig.getServletContext().getContextPath() + Path.MANAGER_MANAGE_ORDERS,
                        filterConfig.getServletContext().getContextPath() + Path.MANAGER_MANAGE_TOURS,
                        filterConfig.getServletContext().getContextPath() + Path.MANAGER_EDIT_DISCOUNT));

        accessMap.put(ADMIN,
                Arrays.asList(filterConfig.getServletContext().getContextPath() + Path.LOGOUT,
                        filterConfig.getServletContext().getContextPath() + Path.MAIN,
                        filterConfig.getServletContext().getContextPath() + Path.ADMIN_PAGE,
                        filterConfig.getServletContext().getContextPath() + Path.ERROR_404,
                        filterConfig.getServletContext().getContextPath() + Path.ERROR_503,
                        filterConfig.getServletContext().getContextPath() + Path.ADMIN_MANAGE_USERS,
                        filterConfig.getServletContext().getContextPath() + Path.ADMIN_MANAGE_ORDERS,
                        filterConfig.getServletContext().getContextPath() + Path.ADMIN_MANAGE_TOURS,
                        filterConfig.getServletContext().getContextPath() + Path.ADMIN_CREATE_TOUR,
                        filterConfig.getServletContext().getContextPath() + Path.ADMIN_CREATE_TOUR_FROM,
                        filterConfig.getServletContext().getContextPath() + Path.TOUR_ORDER,
                        filterConfig.getServletContext().getContextPath() + Path.ORDER_FORM,
                        filterConfig.getServletContext().getContextPath() + Path.MY_ORDERS,
                        filterConfig.getServletContext().getContextPath() + Path.EDIT_PROFILE,
                        filterConfig.getServletContext().getContextPath() + Path.PROFILE_VIEW,
                        filterConfig.getServletContext().getContextPath() + Path.ADMIN_EDIT_DISCOUNT));

        log.debug("Filter initialized");
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        Role userRole = (Role) httpRequest.getSession().getAttribute("role");
        log.trace("got role from session: " + userRole);

        if (userRole == null){
            httpRequest.getSession().setAttribute("role", UNKNOWN);
            userRole = UNKNOWN;
            log.trace("role from session is null, set role : " + UNKNOWN);
        }

        String login = (String)httpRequest.getSession().getAttribute("login");
        log.trace("got login from session: " + login);
        if ( login != null && !(CommandUtility.userIsLogged(httpRequest, login)) ){
            log.trace("login exists but not in login cache" + login);
            httpRequest.getSession().invalidate();
            log.trace("invalidate session: " + login);
            httpResponse.sendRedirect(filterConfig.getServletContext().getContextPath() + "/");
            log.trace("redirect to main: ");
            return;
        }

        String URI = httpRequest.getRequestURI();
        if (  !(accessMap.get(userRole).contains(URI))  ) {
            httpResponse.sendRedirect(filterConfig.getServletContext().getContextPath() + Path.ERROR_404);
            log.error("AccessMap(" + userRole + ") doesn't contain requestURI: " + URI);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.debug("Filter destroyed");

    }
}
