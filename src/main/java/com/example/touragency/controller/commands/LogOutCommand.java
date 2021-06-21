package com.example.touragency.controller.commands;
import com.example.touragency.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandUtility.removeUserRole(request);
        User user = (User)request.getSession().getAttribute("user");
        CommandUtility.deleteFromLoginCache(request, user.getLogin());
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/main.jsp");
    }
}
