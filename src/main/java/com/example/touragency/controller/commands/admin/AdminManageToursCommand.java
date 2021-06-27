package com.example.touragency.controller.commands.admin;

import com.example.touragency.Tools;
import com.example.touragency.constants.Path;
import com.example.touragency.controller.commands.Paginator;
import com.example.touragency.controller.commands.manager.ManageToursCommand;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.enums.TourCategory;
import com.example.touragency.model.entity.enums.TourStatus;
import com.example.touragency.model.service.TourService;
import com.example.touragency.model.service.factory.ServiceFactory;
import com.example.touragency.validation.InvalidDataException;
import com.example.touragency.validation.tour.TourValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class AdminManageToursCommand extends ManageToursCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TourService tourService = ServiceFactory.getInstance().createTourService();

        try {
            updateTourFromRequest(request, tourService);
            deleteTourFromRequest(request, tourService);
        } catch (ServiceException | InvalidDataException e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
        }
        try {
            new Paginator<>(request, tourService).makePagination(this);
        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendRedirect(request.getServletContext().getContextPath() + Path.ERROR_503);
            return;
        }
        request.getRequestDispatcher("/admin/manage_tours.jsp").forward(request, response);


    }


    private void deleteTourFromRequest(HttpServletRequest request, TourService tourService) throws ServiceException {
        String tourName = request.getParameter("deleteTour");
        if (tourName == null) return;
        tourService.deleteByName(tourName);
    }

    private void updateTourFromRequest(HttpServletRequest request,
                                       TourService tourService) throws ServiceException, InvalidDataException {
        String id = request.getParameter("id");
        if (id == null) return;

        String country = request.getParameter("country");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String maxTickets = request.getParameter("maxTickets");
        String minTickets = request.getParameter("minTickets");
        String takenTickets = request.getParameter("takenTickets");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String city = request.getParameter("city");
        String hotelName = request.getParameter("hotelName");
        String category = request.getParameter("category");
        String status = request.getParameter("status");

        TourValidator.createValidator().checkTourIsValid(name, country, city, price, hotelName,
                 maxTickets, takenTickets, startDate, endDate);

        tourService.update(Integer.parseInt(id), name, country, new BigDecimal(price), Integer.parseInt(maxTickets),
                Integer.parseInt(takenTickets), Tools.getCalendarFromString(startDate),
                Tools.getCalendarFromString(endDate), TourCategory.getById(Integer.parseInt(category)),
                TourStatus.getById(Integer.parseInt(status)), hotelName, city);
    }




}
