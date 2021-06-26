package com.example.touragency.controller;


import com.example.touragency.controller.commands.*;
import com.example.touragency.controller.commands.admin.CreateTourCommand;
import com.example.touragency.controller.commands.admin.AdminManageOrdersCommand;
import com.example.touragency.controller.commands.admin.ManageToursCommand;
import com.example.touragency.controller.commands.admin.ManageUsersCommand;
import com.example.touragency.controller.commands.client.*;
import com.example.touragency.controller.commands.manager.ManageOrdersCommand;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;


public class HelloServlet extends HttpServlet {

    private final Map<String, Command> commands = new HashMap<>();


    public void init() {
        getServletConfig().getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("login", new LoginCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("register", new RegisterCommand());
        commands.put("admin/manage_users", new ManageUsersCommand());
        commands.put("admin/manage_orders", new AdminManageOrdersCommand());
        commands.put("manager/manage_orders", new ManageOrdersCommand());
        commands.put("admin/manage_tours", new ManageToursCommand());
        commands.put("admin/create_tour", new CreateTourCommand());
        commands.put("", new MainCommand());
        commands.put("order_form", new OrderFormCommand());
        commands.put("order", new OrderCommand());
        commands.put("my_orders", new MyOrdersCommand());
        commands.put("edit_profile", new EditProfileCommand());
        commands.put("profile_view", new ProfileViewCommand());

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        try {
            processRequest(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/tour_agency_war_exploded/" , "");
        Command command = commands.getOrDefault(path ,
                null);
        command.execute(request, response);

    }

    public void destroy() {
    }
}