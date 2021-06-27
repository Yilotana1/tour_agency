package com.example.touragency.controller.commands;

import com.example.touragency.constants.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandUtility.removeUserRole(request);
        String login = (String)request.getSession().getAttribute("login");
        CommandUtility.deleteFromLoginCache(request, login);
        response.sendRedirect(request.getContextPath() + Path.MAIN);
    }
}
