package com.example.touragency.controller.listeners;

import com.example.touragency.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;


public class SessionListener implements HttpSessionListener {

    public static final Logger log = Logger.getLogger(SessionListener.class);

    public static final int INACTIVE_INTERVAL = 20*60;
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setMaxInactiveInterval(INACTIVE_INTERVAL);
        log.debug("Session created: maxInactiveInterval: " + INACTIVE_INTERVAL);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute("loggedUsers");


        String login = ((String) httpSessionEvent.getSession().getAttribute("login"));
        loggedUsers.remove(login);
        log.trace("User login deleted from login cache, user login: " + login);

        log.debug("Session destroyed");
    }
}
