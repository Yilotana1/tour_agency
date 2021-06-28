package com.example.touragency.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.touragency.constants.Path;
import com.example.touragency.exceptions.*;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.service.factory.ServiceFactory;
import com.example.touragency.validation.user.UserValidator;
import com.example.touragency.validation.InvalidDataException;
import org.apache.log4j.Logger;

import static com.example.touragency.controller.commands.CommandUtility.*;
import static com.example.touragency.model.entity.enums.Role.*;

import java.io.IOException;


public class LoginCommand implements Command {

    public final static Logger log = Logger.getLogger(LoginCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command started executing");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        log.trace("got login and password from request: login = " + login + ", password = " + password);

        UserValidator validator = UserValidator.createUserValidator();
        try {
            validator.checkLoginIsValid(login);
            validator.checkPasswordIsValid(password);
            log.trace("User validator: login and password are valid");
        } catch (InvalidDataException e) {
            request.setAttribute("error", e.getMessage());
            log.error("User validator error:" + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        if (userIsLogged(request, login)) {
            request.setAttribute("error", "It seems like you are already in the system");
            log.error("User is in already in the system: login = " + login);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        addUserToLoginCache(request, login);
        log.trace("added user to login cache: login = " + login);

        try {
            User user = ServiceFactory.getInstance().createUserService().signIn(login, password).get();

            switch (user.getRole()) {
                case CLIENT: {
                    CommandUtility.setUserRole(request, CLIENT);
                    request.getSession().setAttribute("login", user.getLogin());
                    response.sendRedirect(request.getContextPath() + Path.PROFILE_VIEW);
                    log.trace("login was successful, redirect to profile");
                    break;
                }
                case MANAGER: {
                    CommandUtility.setUserRole(request, MANAGER);
                    request.getSession().setAttribute("login", user.getLogin());
                    response.sendRedirect(request.getContextPath() + Path.PROFILE_VIEW);
                    log.trace("login was successful, redirect to profile");
                    break;
                }
                case ADMIN: {
                    CommandUtility.setUserRole(request, ADMIN);
                    request.getSession().setAttribute("login", user.getLogin());
                    response.sendRedirect(request.getContextPath() + Path.PROFILE_VIEW);
                    log.trace("login was successful, redirect to profile");
                    break;
                }
            }
            request.getSession().setAttribute("login", user.getLogin());

        } catch (ServiceException e) {
            deleteFromLoginCache(request, login);
            request.setAttribute("error", e.getMessage());
            log.error(e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }
}
