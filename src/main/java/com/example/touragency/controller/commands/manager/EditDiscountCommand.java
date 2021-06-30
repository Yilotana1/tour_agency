package com.example.touragency.controller.commands.manager;

import com.example.touragency.constants.Path;
import com.example.touragency.controller.commands.Command;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.service.DiscountService;
import com.example.touragency.model.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditDiscountCommand implements Command {

    public final static Logger log = Logger.getLogger(EditDiscountCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command started executing");

        DiscountService discountService = ServiceFactory.getInstance().createDiscountService();
        int percentStep = Integer.parseInt(request.getParameter("percentStep"));
        int maxPercent = Integer.parseInt(request.getParameter("maxPercent"));

        try {
            discountService.update(percentStep, maxPercent);
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            log.error(e.getMessage());
            request.getRequestDispatcher(Path.MANAGER_MANAGE_ORDERS).forward(request, response);
            return;
        }

        request.getRequestDispatcher(Path.MANAGER_MANAGE_ORDERS).forward(request, response);
        log.debug("Forward to " + Path.MANAGER_MANAGE_ORDERS);
    }
}
