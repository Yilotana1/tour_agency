package com.example.touragency.controller.commands.admin;

import com.example.touragency.constants.Path;
import com.example.touragency.controller.commands.Command;
import com.example.touragency.controller.commands.CommandUtility;
import com.example.touragency.controller.commands.utils.Paginator;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.entity.enums.UserStatus;
import com.example.touragency.model.service.Service;
import com.example.touragency.model.service.UserService;
import com.example.touragency.model.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
        * Shows tour list and presents functionality to block or unblock users.
        */
public class ManageUsersCommand implements Command, Paginator.NextPageSupplier<User> {

    public final static Logger log = Logger.getLogger(ManageUsersCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command started executing");

        UserService userService = ServiceFactory.getInstance().createUserService();

        try {
            updateUserFromRequest(request, userService);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            request.setAttribute("path", request.getServletContext().getContextPath() + Path.ADMIN_MANAGE_USERS);
        }

        try {
            new Paginator<>(request, userService).makePagination(this);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            response.sendRedirect(request.getServletContext().getContextPath() + Path.ERROR_503);
            return;
        }

        request.getRequestDispatcher("/admin/manage_users.jsp").forward(request, response);
        log.debug("Forward to /admin/manage_users.jsp");
    }


    private void updateUserFromRequest(HttpServletRequest request, UserService userService) throws ServiceException {
        String id = request.getParameter("id");

        if (id == null) return;

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserStatus status = UserStatus.getById(Integer.parseInt(request.getParameter("status")));
        Role role = Role.getById(Integer.parseInt(request.getParameter("role")));

        userService.update(Integer.parseInt(id), firstName, lastName, phone, email, status, login, password, role);
        CommandUtility.deleteFromLoginCache(request, login);

    }


    private List<User> getSearchedUserAtList(String login, UserService userService) throws ServiceException {
        Optional<User> user = userService.getByLogin(login);
        return user.map(Arrays::asList).orElseGet(ArrayList::new);

    }


    @Override
    public List<User> getNextPageContent(HttpServletRequest request, int page, int maxPageSize, Service<User> userService) throws ServiceException {
        if (request.getParameter("search") != null) {
            String login = request.getParameter("search");
            return getSearchedUserAtList(login, (UserService) userService);
        }

        String orderBy = request.getParameter("order");
        if (orderBy == null) orderBy = "";
        request.setAttribute("order", orderBy);

        List<User> users = userService.getPage(page, maxPageSize);
        switch (orderBy) {
            case "clients":
                users = ((UserService) userService).getPageClientsFirst(page, maxPageSize);
                break;
            case "managers":
                users = ((UserService) userService).getPageManagersFirst(page, maxPageSize);
                break;
            case "non_blocked":
                users = ((UserService) userService).getPageNonBlockedFirst(page, maxPageSize);
                break;
            case "blocked":
                users = ((UserService) userService).getPageBlockedFirst(page, maxPageSize);
                break;
        }
        return users;
    }
}

