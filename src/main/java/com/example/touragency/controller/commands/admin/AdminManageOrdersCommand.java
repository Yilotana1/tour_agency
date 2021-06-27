package com.example.touragency.controller.commands.admin;

import com.example.touragency.constants.Path;
import com.example.touragency.controller.commands.Command;
import com.example.touragency.controller.commands.Paginator;
import com.example.touragency.controller.commands.manager.ManageOrdersCommand;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.Discount;
import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.enums.OrderStatus;
import com.example.touragency.model.service.DiscountService;
import com.example.touragency.model.service.OrderService;
import com.example.touragency.model.service.Service;
import com.example.touragency.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AdminManageOrdersCommand extends ManageOrdersCommand implements Paginator.NextPageSupplier<Order> {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OrderService orderService = ServiceFactory.getInstance().createOrderService();


        try {
            fillDiscountForm(request);
            super.updateOrderFromRequest(request, orderService);
        } catch (ServiceException e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
        }

        try {
            new Paginator<>(request, orderService).makePagination(this);
        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendRedirect(request.getServletContext().getContextPath() + Path.ERROR_503);
            return;
        }

        request.setAttribute("path", request.getServletContext().getContextPath() + Path.ADMIN_MANAGE_ORDERS);
        request.getRequestDispatcher("/manager/manage_orders.jsp").forward(request, response);
    }
}