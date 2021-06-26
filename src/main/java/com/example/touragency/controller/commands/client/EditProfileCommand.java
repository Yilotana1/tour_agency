package com.example.touragency.controller.commands.client;

import com.example.touragency.controller.commands.Command;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.entity.enums.UserStatus;
import com.example.touragency.model.service.UserService;
import com.example.touragency.model.service.factory.ServiceFactory;
import com.example.touragency.validation.InvalidDataException;
import com.example.touragency.validation.user.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProfileCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = ServiceFactory.getInstance().createUserService();
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Role role = (Role)request.getSession().getAttribute("role");
        UserStatus status = UserStatus.getById(Integer.parseInt(request.getParameter("status")));
        User user = User.createUser(firstName, lastName, phone, email, status, login, password, role);
        try {
            UserValidator.createUserValidator().checkUserIsValid(user);
        } catch (InvalidDataException e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            switch (role){
                case ADMIN: request.getRequestDispatcher("/admin/admin_page.jsp").forward(request, response);
                break;
                case MANAGER:request.getRequestDispatcher("/manager/manager_page.jsp").forward(request,response);
                break;
                case CLIENT:request.getRequestDispatcher("client/client_page.jsp").forward(request, response);
                break;
            }
            return;
        }
        try {
            userService.update(user);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        switch (role){
            case ADMIN: request.getRequestDispatcher("/admin/admin_page.jsp").forward(request, response);
                break;
            case MANAGER:request.getRequestDispatcher("/manager/manager_page.jsp").forward(request,response);
                break;
            case CLIENT:request.getRequestDispatcher("client/client_page.jsp").forward(request, response);
                break;
        }
    }
}
