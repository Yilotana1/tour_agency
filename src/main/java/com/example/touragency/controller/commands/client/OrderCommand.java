package com.example.touragency.controller.commands.client;

import com.example.touragency.controller.commands.Command;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.service.OrderService;
import com.example.touragency.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OrderService orderService = ServiceFactory.getInstance().createOrderService();
        try {
            orderService.applyForOrder(
                    Integer.parseInt(request.getParameter("tourId")),
                    (String)request.getSession().getAttribute("login"));

        } catch (ServiceException e) {
            e.printStackTrace();
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/order_confirm.jsp").forward(request, response);
        }


        request.setAttribute("message", "Your order is opened. Our manager will contact you in soon to let information about the next steps and payment");
        request.getRequestDispatcher("/order_confirm.jsp").forward(request, response);
    }
}
