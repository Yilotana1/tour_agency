package com.example.touragency.controller.commands;

import com.example.touragency.constants.Path;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.exceptions.UserAlreadyExistsException;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.entity.enums.UserStatus;
import com.example.touragency.model.service.UserService;
import com.example.touragency.model.service.factory.ServiceFactory;
import com.example.touragency.validation.user.UserValidator;
import com.example.touragency.validation.InvalidDataException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegisterCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = User.createUser(
                request.getParameter("firstname"),
                request.getParameter("lastname"),
                request.getParameter("phone"),
                request.getParameter("email"),
                UserStatus.NON_BLOCKED,
                request.getParameter("login"),
                request.getParameter("password"),
                Role.CLIENT);


        try {
            UserValidator.createUserValidator().checkUserIsValid(user);
        } catch (InvalidDataException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            e.printStackTrace();
        }


        UserService service = ServiceFactory.getInstance().createUserService();
        try {

            service.signUp(user);

        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            e.printStackTrace();
        }

        response.sendRedirect(
                request.getServletContext().getContextPath() + Path.LOGIN +
                        "?login=" + user.getLogin() + "&password=" + user.getPassword());



    }

}
