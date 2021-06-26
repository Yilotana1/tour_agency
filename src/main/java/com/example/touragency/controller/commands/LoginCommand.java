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

import static com.example.touragency.controller.commands.CommandUtility.*;
import static com.example.touragency.model.entity.enums.Role.*;

import java.io.IOException;



public class LoginCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserValidator validator = UserValidator.createUserValidator();
        try{
           validator.checkLoginIsValid(login);
           validator.checkPasswordIsValid(password);
        } catch (InvalidDataException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
            e.printStackTrace();
        }

        if(userIsLogged(request, login)){
            request.setAttribute("error", "It seems like you are already in the system");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        addUserToLoginCache(request, login);

        try {
            User user = ServiceFactory.getInstance().createUserService().signIn(login, password);

            switch (user.getRole()) {
                case CLIENT: {
                    CommandUtility.setUserRole(request, CLIENT);
                    request.getSession().setAttribute("login", user.getLogin());
                    response.sendRedirect(request.getContextPath() + Path.PROFILE_VIEW);
                    break;
                }
                case MANAGER: {
                    CommandUtility.setUserRole(request, MANAGER);
                    request.getSession().setAttribute("login", user.getLogin());
                    response.sendRedirect(request.getContextPath() + Path.PROFILE_VIEW);
                    break;
                }
                case ADMIN: {
                    CommandUtility.setUserRole(request, ADMIN);
                    request.getSession().setAttribute("login", user.getLogin());
                    response.sendRedirect(request.getContextPath() + Path.PROFILE_VIEW);
                    break;
                }
            }

        } catch (InvalidCredentialsException e) {
            deleteFromLoginCache(request, login);
            request.setAttribute("error", "User doesn't exist in the system, please check your login or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }


    }
}
