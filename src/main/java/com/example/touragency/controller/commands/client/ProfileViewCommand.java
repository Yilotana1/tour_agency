package com.example.touragency.controller.commands.client;

import com.example.touragency.constants.Path;
import com.example.touragency.controller.commands.Command;
import com.example.touragency.controller.commands.manager.EditDiscountCommand;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.service.UserService;
import com.example.touragency.model.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileViewCommand implements Command {

    public final static Logger log = Logger.getLogger(ProfileViewCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command started executing");

        UserService userService = ServiceFactory.getInstance().createUserService();
        User user = null;
        try {
            user = userService.getByLogin((String) request.getSession().getAttribute("login")).get();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            response.sendRedirect(request.getServletContext().getContextPath() + "/503error.jsp");
        }
        request.setAttribute("user", user);

        switch (user.getRole()) {
            case CLIENT:
                request.getRequestDispatcher(Path.CLIENT_PAGE).forward(request, response);
                break;
            case MANAGER:
                request.getRequestDispatcher(Path.MANAGER_PAGE).forward(request, response);
                break;
            case ADMIN:
                request.getRequestDispatcher(Path.ADMIN_PAGE).forward(request, response);
                break;
        }

        log.debug("Forward to profile jsp page");

    }

}
