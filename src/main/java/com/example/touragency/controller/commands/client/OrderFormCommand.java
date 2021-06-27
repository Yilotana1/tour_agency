package com.example.touragency.controller.commands.client;

import com.example.touragency.constants.Path;
import com.example.touragency.controller.commands.Command;
import com.example.touragency.model.entity.Discount;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.service.DiscountService;
import com.example.touragency.model.service.TourService;
import com.example.touragency.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderFormCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("role").equals(Role.UNKNOWN)){
            response.sendRedirect(request.getServletContext().getContextPath() + Path.LOGIN_FORM);
            return;
        }

        TourService tourService = ServiceFactory.getInstance().createTourService();
        Tour tour = tourService.getById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("tour", tour);
        request.getRequestDispatcher("/order.jsp").forward(request, response);
    }
}
