package com.example.touragency.controller;


import com.example.touragency.constants.Path;
import com.example.touragency.controller.commands.*;
import com.example.touragency.controller.commands.admin.CreateTourCommand;
import com.example.touragency.controller.commands.admin.AdminManageOrdersCommand;
import com.example.touragency.controller.commands.admin.AdminManageToursCommand;
import com.example.touragency.controller.commands.admin.ManageUsersCommand;
import com.example.touragency.controller.commands.client.*;
import com.example.touragency.controller.commands.manager.EditDiscountCommand;
import com.example.touragency.controller.commands.manager.ManageOrdersCommand;
import com.example.touragency.controller.commands.manager.ManageToursCommand;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;


public class Servlet extends HttpServlet {

    private final Map<String, Command> commands = new HashMap<>();

    public final static Logger log = Logger.getLogger(Servlet.class);

    public void init() {
        getServletConfig().getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put(Path.LOGIN, new LoginCommand());
        commands.put(Path.LOGOUT, new LogOutCommand());
        commands.put(Path.REGISTER, new RegisterCommand());
        commands.put(Path.ADMIN_MANAGE_USERS, new ManageUsersCommand());
        commands.put(Path.ADMIN_MANAGE_ORDERS, new AdminManageOrdersCommand());
        commands.put(Path.ADMIN_MANAGE_TOURS, new AdminManageToursCommand());
        commands.put(Path.ADMIN_CREATE_TOUR, new CreateTourCommand());
        commands.put(Path.ADMIN_EDIT_DISCOUNT, new EditDiscountCommand());
        commands.put(Path.MANAGER_MANAGE_ORDERS, new ManageOrdersCommand());
        commands.put(Path.MANAGER_MANAGE_TOURS, new ManageToursCommand());
        commands.put(Path.MANAGER_EDIT_DISCOUNT, new EditDiscountCommand());
        commands.put(Path.MAIN, new MainCommand());
        commands.put(Path.ORDER_FORM, new OrderFormCommand());
        commands.put(Path.TOUR_ORDER, new OrderCommand());
        commands.put(Path.MY_ORDERS, new MyOrdersCommand());
        commands.put(Path.EDIT_PROFILE, new EditProfileCommand());
        commands.put(Path.PROFILE_VIEW, new ProfileViewCommand());

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("Servlet started");
        response.setContentType("text/plain");
        try {
            processRequest(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        log.debug("Servlet finished");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();

        log.trace("got URI from request: " + path);

        path = path.replaceAll(".*/tour_agency_war_exploded", "");
        Command command = commands.getOrDefault(path,
                null);
        log.trace("Retrieved command from path");

        command.execute(request, response);

        log.trace("Command executed");
    }

    public void destroy() {
    }
}