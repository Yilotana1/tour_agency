package com.example.touragency.controller.commands;

import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.service.Service;
import com.example.touragency.model.service.TourService;
import com.example.touragency.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MainCommand implements Command, Paginator.NextPageSupplier<Tour> {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TourService tourService = ServiceFactory.getInstance().createTourService();

        new Paginator<>(request, tourService).makePagination(this);

        request.getRequestDispatcher("/main.jsp").forward(request, response);
    }

    @Override
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
