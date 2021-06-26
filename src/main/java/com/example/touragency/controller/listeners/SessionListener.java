package com.example.touragency.controller.listeners;

import com.example.touragency.model.entity.User;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;


public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setMaxInactiveInterval(20 * 60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute("loggedUsers");


        String login = ((User) httpSessionEvent.getSession().getAttribute("user")).getLogin();
        loggedUsers.remove(login);
        httpSessionEvent.getSession().removeAttribute("user");
    }
}
