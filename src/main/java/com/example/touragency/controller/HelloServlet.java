package com.example.touragency.controller;


import com.example.touragency.controller.commands.Command;
import com.example.touragency.controller.commands.LogOutCommand;
import com.example.touragency.controller.commands.LoginCommand;
import com.example.touragency.controller.commands.RegisterCommand;
import com.example.touragency.controller.commands.admin.ManageOrdersCommand;
import com.example.touragency.controller.commands.admin.ManageToursCommand;
import com.example.touragency.controller.commands.admin.ManageUsersCommand;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;


public class HelloServlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();


    public void init() {
        getServletConfig().getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("login", new LoginCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("register", new RegisterCommand());
        commands.put("admin/manage_users", new ManageUsersCommand());
        commands.put("admin/manage_orders", new ManageOrdersCommand());
        commands.put("admin/manage_tours", new ManageToursCommand());

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