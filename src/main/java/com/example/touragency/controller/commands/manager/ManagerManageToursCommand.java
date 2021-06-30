package com.example.touragency.controller.commands.manager;

import com.example.touragency.constants.Path;
import com.example.touragency.controller.commands.utils.Paginator;
import com.example.touragency.controller.commands.Command;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.enums.TourStatus;
import com.example.touragency.model.service.Service;
import com.example.touragency.model.service.TourService;
import com.example.touragency.model.service.factory.ServiceFactory;
import com.example.touragency.validation.InvalidDataException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Shows tour list and presents functionality to change and delete tour.
 */
public class ManagerManageToursCommand implements Command, Paginator.NextPageSupplier<Tour> {

    public final static Logger log = Logger.getLogger(ManagerManageToursCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command started executing");
        TourService tourService = ServiceFactory.getInstance().createTourService();

        try {
            updateTourFromRequest(request, tourService);
        } catch (ServiceException | InvalidDataException e) {
            request.setAttribute("error", e.getMessage());
            log.error(e.getMessage());
            request.getRequestDispatcher("/manager/manage_tours.jsp").forward(request, response);
            return;

        }
        try {
            new Paginator<>(request, tourService).makePagination(this);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            response.sendRedirect(request.getServletContext().getContextPath() + Path.ERROR_503);
            return;
        }
        request.getRequestDispatcher("/manager/manage_tours.jsp").forward(request, response);

        log.debug("Forward to manager/manage_tours.jsp");

    }


    private void updateTourFromRequest(HttpServletRequest request,
                                       TourService tourService) throws ServiceException, InvalidDataException {

        String id = request.getParameter("id");
        if (id == null) return;

        String status = request.getParameter("status");

        tourService.changeStatus(Integer.parseInt(id), TourStatus.getById(Integer.parseInt(status)));

    }


    // This method defines previous page order parameters and retrieve according data to show on the next page
    public List<Tour> getNextPageContent(HttpServletRequest request, int page, int maxPageSize, Service<Tour> tourService) throws ServiceException {
        String orderBy = request.getParameter(Paginator.ORDER);
        if (orderBy == null) orderBy = "";
        request.setAttribute("order", orderBy);

        if (orderBy.equals("name")) {
            request.setAttribute("name", request.getParameter("name"));
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
            case "name":
                tours = getToursPageBySearchName(request, ((TourService) tourService), page, maxPageSize);
                break;
        }
        return tours;
    }


    private List<Tour> getToursPageBySearchName(HttpServletRequest request, TourService tourService, int page, int maxPageSize) throws ServiceException {
            List<Tour> tours = tourService.getPageName(page, maxPageSize, request.getParameter("name"));
            if (tours.size() > 0) {
                return tours;
            }

        return new ArrayList<>();
    }

}
