package com.example.touragency.controller.commands.admin;

import com.example.touragency.Tools;
import com.example.touragency.controller.commands.Command;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.enums.TourCategory;
import com.example.touragency.model.entity.enums.TourStatus;
import com.example.touragency.model.service.TourService;
import com.example.touragency.model.service.factory.ServiceFactory;
import com.example.touragency.model.service.impl.TourServiceImpl;
import com.example.touragency.validation.InvalidDataException;
import com.example.touragency.validation.tour.TourValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class CreateTourCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String price = request.getParameter("price");
        String hotelName = request.getParameter("hotelName");
        String minTickets = request.getParameter("minTickets");
        String maxTickets = request.getParameter("maxTickets");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        TourCategory category = TourCategory.getById(Integer.parseInt(request.getParameter("category")));
        TourStatus status = TourStatus.getById(Integer.parseInt(request.getParameter("status")));


        TourService tourService = ServiceFactory.getInstance().createTourService();

        try {
            new TourValidator().checkTourIsValid(name, country, city, price, hotelName,
                   maxTickets, String.valueOf(TourServiceImpl.INITIAL_TAKEN_TICKETS), startDate, endDate);

            tourService.create(name, country, new BigDecimal(price), Integer.parseInt(maxTickets),
                    Tools.getCalendarFromString(startDate),
                    Tools.getCalendarFromString(endDate), category, status, hotelName, city);

        } catch (InvalidDataException | ServiceException e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/admin/create_tour.jsp").forward(request, response);
            return;
        }

        response.sendRedirect(request.getServletContext().getContextPath() + "/admin/manage_tours");

    }

}