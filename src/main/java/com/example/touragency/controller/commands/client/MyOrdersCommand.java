package com.example.touragency.controller.commands.client;

import com.example.touragency.controller.commands.Command;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.service.OrderService;
import com.example.touragency.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyOrdersCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = ServiceFactory.getInstance().createOrderService();
        List<Order> orders = null;
        try {
            orders = orderService
                    .getByLogin(
                            (String)request.getSession().getAttribute("login")
                    );
        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendRedirect(request.getServletContext().getContextPath() + "/503error.jsp");
        }
        request.setAttribute("items", orders);
        request.getRequestDispatcher("/my_orders.jsp").forward(request, response);
    }
}
