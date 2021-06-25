package com.example.touragency.controller.commands.admin;

import com.example.touragency.Tools;
import com.example.touragency.controller.commands.Paginator;
import com.example.touragency.controller.commands.Command;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.enums.TourCategory;
import com.example.touragency.model.entity.enums.TourStatus;
import com.example.touragency.model.service.Service;
import com.example.touragency.model.service.TourService;
import com.example.touragency.model.service.factory.ServiceFactory;
import com.example.touragency.validation.InvalidDataException;
import com.example.touragency.validation.tour.TourValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ManageToursCommand implements Command, Paginator.NextPageSupplier<Tour> {

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
        new Paginator<>(request, tourService).makePagination(this);
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
                minTickets, maxTickets, takenTickets, startDate, endDate);

        tourService.update(Integer.parseInt(id), name, country, new BigDecimal(price), Integer.parseInt(maxTickets),
                Integer.parseInt(takenTickets), Tools.getCalendarFromString(startDate),
                Tools.getCalendarFromString(endDate), TourCategory.getById(Integer.parseInt(category)),
                TourStatus.getById(Integer.parseInt(status)), hotelName, city);
    }


    // This method defines previous page order parameters and retrieve according data to show on the next page
    public List<Tour> getNextPageContent(HttpServletRequest request, int page, int maxPageSize, Service<Tour> tourService) {
        String orderBy = request.getParameter(Paginator.ORDER);
        if (orderBy == null) orderBy = "";
        request.setAttribute("order", orderBy);

        if (orderBy.equals("country")) {
            request.setAttribute("country", request.getParameter("country"));
        }
        List<Tour> tours = tourService.getPage(page, maxPageSize);
        switch (orderBy) {
            case "burning":
                tours = ((TourService) tourService).getPageBurningFirst(page, maxPageSize);
                break;
            case "non_burning":
                tours = ((TourService) tourService).getPageNonBurningFirst(page, maxPageSize);
                break;
            case "high_hotel_stars":
                tours = ((TourService) tourService).getPageHighHotelStarsFirst(page, maxPageSize);
                break;
            case "low_hotel_stars":
                tours = ((TourService) tourService).getPageLowHotelStarsFirst(page, maxPageSize);
                break;
            case "high_price":
                tours = ((TourService) tourService).getPageHighPriceFirst(page, maxPageSize);
                break;
            case "low_price":
                tours = ((TourService) tourService).getPageLowPriceFirst(page, maxPageSize);
                break;
            case "excursion":
                tours = ((TourService) tourService).getPageExcursion(page, maxPageSize);
                break;
            case "rest":
                tours = ((TourService) tourService).getPageRest(page, maxPageSize);
                break;
            case "shopping":
                tours = ((TourService) tourService).getPageShopping(page, maxPageSize);
                break;
            case "country":
                tours = getToursPageBySearchCountry(request, ((TourService) tourService), page, maxPageSize);
                break;
        }
        return tours;
    }


    private List<Tour> getToursPageBySearchCountry(HttpServletRequest request, TourService tourService, int page, int maxPageSize) {
        try {
            List<Tour> tours = tourService.getPageCountry(page, maxPageSize, request.getParameter("country"));
            if (tours.size() > 0) {
                return tours;
            }
        } catch (NoSuchElementException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

}
