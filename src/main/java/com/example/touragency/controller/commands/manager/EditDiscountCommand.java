package com.example.touragency.controller.commands.manager;

import com.example.touragency.constants.Path;
import com.example.touragency.controller.commands.Command;
import com.example.touragency.model.service.DiscountService;
import com.example.touragency.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditDiscountCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiscountService discountService = ServiceFactory.getInstance().createDiscountService();
        int percentStep = Integer.parseInt(request.getParameter("percentStep"));
        int maxPercent = Integer.parseInt(request.getParameter("maxPercent"));

        discountService.update(percentStep, maxPercent);


        request.getRequestDispatcher(Path.ADMIN_MANAGE_ORDERS).forward(request, response);
    }
}
