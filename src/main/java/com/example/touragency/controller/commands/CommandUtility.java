package com.example.touragency.controller.commands;

import com.example.touragency.model.entity.enums.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtility {


    public static void changeUserRole(HttpServletRequest request, Role role){
        removeUserRole(request);
        setUserRole(request, role);
    }

    public static void setUserRole(HttpServletRequest request,
                            Role role) {
        HttpSession session = request.getSession();
        session.setAttribute("role", role);
    }

    public static void removeUserRole(HttpServletRequest request){
        request.getSession().removeAttribute("role");
        CommandUtility.setUserRole(request, Role.UNKNOWN);
    }

    public static void deleteFromLoginCache(HttpServletRequest request, String login){
        HashSet<String> loggedUsers = (HashSet<String>)request.getServletContext().getAttribute("loggedUsers");
        loggedUsers.remove(login);
    }

    public static boolean userIsLogged(HttpServletRequest request, String login){
        HashSet<String> loggedUsers = (HashSet<String>) request.getServletContext()
                .getAttribute("loggedUsers");

        if(loggedUsers.stream().anyMatch(login::equals)){
            return true;
        }
        return false;
    }

    public static void addUserToLoginCache(HttpServletRequest request, String login){
        HashSet<String> loggedUsers = (HashSet<String>) request.getServletContext()
                .getAttribute("loggedUsers");
        loggedUsers.add(login);
        request.getServletContext()
                .setAttribute("loggedUsers", loggedUsers);

    }}
