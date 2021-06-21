package com.example.touragency.controller.commands.admin;

import com.example.touragency.controller.commands.Command;
import com.example.touragency.controller.commands.CommandUtility;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.entity.enums.UserStatus;
import com.example.touragency.model.service.UserService;
import com.example.touragency.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ManageUsersCommand implements Command {


    private final static int MAX_PAGE_SIZE = 5;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = ServiceFactory.getInstance().createUserService();

        updateUserFromRequest(request, userService);

        //pagination
        int usersNumber = userService.getUserCount();
        int pagesNumber = getPageNumber(usersNumber, MAX_PAGE_SIZE);
        int page = addNextPageIdToRequest(request, pagesNumber);


        List<User> users = defineNextPageContent(request, page, MAX_PAGE_SIZE, userService);

        request.setAttribute("clients", users);
        request.setAttribute("pagesNumber", pagesNumber);
        request.getRequestDispatcher("/admin/manage_users.jsp").forward(request, response);

    }


    private void updateUserFromRequest(HttpServletRequest request, UserService userService){
        String userId = request.getParameter("id");


        if (userId != null) {
            UserStatus status = UserStatus.getById(Integer.parseInt(request.getParameter("status")));
            Role role = Role.getById(Integer.parseInt(request.getParameter("role")));

            try {
                User user = userService.getUserById(Integer.parseInt(userId));
                user.setStatus(status);
                user.setRole(role);
                userService.updateUser(user);
                CommandUtility.deleteFromLoginCache(request, user.getLogin());
            } catch (ServiceException throwables) {
                throwables.printStackTrace();
            }

        }
    }


    private int addNextPageIdToRequest(HttpServletRequest request, int pagesNumber) {
        int page = 1;

        if (request.getParameter("nextPage") != null
                && Integer.parseInt(request.getParameter("nextPage")) < pagesNumber) {

            page = Integer.parseInt(request.getParameter("nextPage")) + 1;
        }

        if (request.getParameter("previousPage") != null
                && Integer.parseInt(request.getParameter("previousPage")) > 1) {

            page = Integer.parseInt(request.getParameter("previousPage")) - 1;
        }

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        request.setAttribute("page", page);

        return page;
    }


    private List<User> defineNextPageContent(HttpServletRequest request, int page, int maxPageSize, UserService userService) {
        String orderBy = request.getParameter("order");
        if (orderBy == null) orderBy = "";
        request.setAttribute("order", orderBy);

        List<User> users = userService.getUsersPage(page, MAX_PAGE_SIZE);
        switch (orderBy) {
            case "clients":
                users = userService.getUsersPageClientsFirst(page, MAX_PAGE_SIZE);
                break;
            case "managers":
                users = userService.getUsersPageManagersFirst(page, MAX_PAGE_SIZE);
                break;
            case "non_blocked":
                users = userService.getUsersPageNonBlockedFirst(page, MAX_PAGE_SIZE);
                break;
            case "blocked":
                users = userService.getUsersPageBlockedFirst(page, MAX_PAGE_SIZE);
                break;
        }
        return users;
    }

    private int getPageNumber(int usersNumber, int pageSize) {
        return (int) Math.ceil((double) usersNumber / pageSize);
    }


}

