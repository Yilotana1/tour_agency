package com.example.touragency.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.touragency.exceptions.*;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.service.factory.ServiceFactory;
import com.example.touragency.validation.user.UserValidator;
import com.example.touragency.validation.user.exceptions.InvalidDataException;

import static com.example.touragency.controller.commands.CommandUtility.*;
import static com.example.touragency.model.entity.enums.Role.*;

import java.io.IOException;



public class LoginCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

//        if (login == null || login.equals("") || password == null || password.equals("")) {
//            request.setAttribute("error", "You have not entered password or login, please input full information");
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//            return;
//        }
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
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect(request.getContextPath() + "/client/client_page.jsp");
                    break;
                }
                case MANAGER: {
                    CommandUtility.setUserRole(request, MANAGER);
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect(request.getContextPath() + "/manager/manager_page.jsp");
                    break;
                }
                case ADMIN: {
                    CommandUtility.setUserRole(request, ADMIN);
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect(request.getContextPath() + "/admin/admin_page.jsp");
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
