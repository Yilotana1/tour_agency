package com.example.touragency.controller.commands;

import com.example.touragency.constants.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutCommand implements Command {
    public final static Logger log = Logger.getLogger(LogOutCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command started executing");
        CommandUtility.removeUserRole(request);

        log.trace("user role removed from session");

        String login = (String) request.getSession().getAttribute("login");
        CommandUtility.deleteFromLoginCache(request, login);
        log.trace("login deleted from login cache: login = " + login);
        response.sendRedirect(request.getContextPath() + Path.MAIN);

        log.debug("Logout passed successfully, redirect to " + Path.MAIN);
    }
}
