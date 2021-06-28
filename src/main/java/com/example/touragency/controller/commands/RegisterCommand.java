package com.example.touragency.controller.commands;

import com.example.touragency.constants.Path;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.entity.enums.UserStatus;
import com.example.touragency.model.service.UserService;
import com.example.touragency.model.service.factory.ServiceFactory;
import com.example.touragency.validation.user.UserValidator;
import com.example.touragency.validation.InvalidDataException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegisterCommand implements Command {

    public final static Logger log = Logger.getLogger(RegisterCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Started executing command");
        User user = getUserFromRequest(request);
        log.trace("Got user from request: login = " + user.getLogin());
        try {
            UserValidator.createUserValidator().checkUserIsValid(user);
            log.trace("User validation passed successfully");
        } catch (InvalidDataException e) {
            request.setAttribute("error", e.getMessage());
            log.error("Validation error: " + e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            log.trace("Forward to register.jsp");
            return;
        }

        UserService service = ServiceFactory.getInstance().createUserService();
        try {

            service.signUp(user);
            log.trace("User signed up successfully");
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            log.error("SighUp error" + e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            log.trace("Forward to register.jsp");
            return;
        }

        response.sendRedirect(
                request.getServletContext().getContextPath() + Path.LOGIN +
                        "?login=" + user.getLogin() + "&password=" + user.getPassword());
        log.debug("Registration passed successfully: redirect to profile");
    }




    private User getUserFromRequest(HttpServletRequest request){
        return User.createUser(
                request.getParameter("firstname"),
                request.getParameter("lastname"),
                request.getParameter("phone"),
                request.getParameter("email"),
                UserStatus.NON_BLOCKED,
                request.getParameter("login"),
                request.getParameter("password"),
                Role.CLIENT);
    }

}
