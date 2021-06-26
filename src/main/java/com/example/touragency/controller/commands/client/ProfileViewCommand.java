package com.example.touragency.controller.commands.client;

import com.example.touragency.constants.Path;
import com.example.touragency.controller.commands.Command;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.service.UserService;
import com.example.touragency.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileViewCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = ServiceFactory.getInstance().createUserService();
        User user = userService.getByLogin((String) request.getSession().getAttribute("login"));
        request.setAttribute("user", user);

        switch (user.getRole()){
            case CLIENT: request.getRequestDispatcher(Path.CLIENT_PAGE).forward(request, response);
            break;
            case MANAGER: request.getRequestDispatcher(Path.MANAGER_PAGE).forward(request, response);
            break;
            case ADMIN: request.getRequestDispatcher(Path.ADMIN_PAGE).forward(request, response);
            break;
        }
    }

}
