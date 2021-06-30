package com.example.touragency.controller.commands.client;

import com.example.touragency.constants.Path;
import com.example.touragency.controller.commands.Command;
import com.example.touragency.controller.commands.CommandUtility;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.entity.enums.UserStatus;
import com.example.touragency.model.service.UserService;
import com.example.touragency.model.service.factory.ServiceFactory;
import com.example.touragency.validation.InvalidDataException;
import com.example.touragency.validation.user.UserValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Edit user profile from profile page
 */
public class EditProfileCommand implements Command {

    public final static Logger log = Logger.getLogger(EditProfileCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command started executing");

        String firstName = request.getParameter("firstname");
        if (firstName == null) {
            request.getRequestDispatcher(Path.PROFILE_VIEW).forward(request, response);
            log.trace("Missclick on edit profile");
            return;
        }

        String lastName = request.getParameter("lastname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Role role = (Role) request.getSession().getAttribute("role");
        UserStatus status = UserStatus.getById(Integer.parseInt(request.getParameter("status")));

        UserService userService = ServiceFactory.getInstance().createUserService();

        try {

            UserValidator.createUserValidator().checkUserIsValid(firstName, lastName, phone, email, login, password);
            userService.update((String) request.getSession().getAttribute("login"), firstName, lastName, phone, email,
                    status, login, password, role);


            CommandUtility.deleteFromLoginCache(request, (String) request.getSession().getAttribute("login"));
            CommandUtility.addUserToLoginCache(request, login);
            request.getSession().removeAttribute("login");
            request.getSession().setAttribute("login", login);


        } catch (InvalidDataException | ServiceException e) {
            log.error(e.getMessage());
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(Path.PROFILE_VIEW).forward(request, response);
            return;
        }

        request.getRequestDispatcher(Path.PROFILE_VIEW).forward(request, response);
        log.debug("Forward to " + Path.PROFILE_VIEW);
    }
}
