package com.example.touragency.controller.commands.client;

import com.example.touragency.constants.Path;
import com.example.touragency.controller.commands.Command;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.service.TourService;
import com.example.touragency.model.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderFormCommand implements Command {

    public final static Logger log = Logger.getLogger(OrderFormCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command started executing");

        if (request.getSession().getAttribute("role").equals(Role.UNKNOWN)) {
            response.sendRedirect(request.getServletContext().getContextPath() + Path.LOGIN_FORM);
            return;
        }

        TourService tourService = ServiceFactory.getInstance().createTourService();
        try {
            Tour tour = tourService.getById(
                    Integer.parseInt(request.getParameter("id")))
                    .orElseThrow(() -> new ServiceException("Tour was removed: tourId = " + request.getParameter("id")));

            request.setAttribute("tour", tour);
            request.getRequestDispatcher("/order.jsp").forward(request, response);
            log.debug("Redirect to order.jsp");

        } catch (ServiceException e) {
            log.error(e.getMessage());
            response.sendRedirect(request.getServletContext().getContextPath() + Path.MAIN);
        }


    }
}
