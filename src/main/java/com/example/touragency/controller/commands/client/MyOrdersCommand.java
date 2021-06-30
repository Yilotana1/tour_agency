package com.example.touragency.controller.commands.client;

import com.example.touragency.controller.commands.Command;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.service.OrderService;
import com.example.touragency.model.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * Show information about client's orders
 */
public class MyOrdersCommand implements Command {

    public final static Logger log = Logger.getLogger(MyOrdersCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command started executing");
        OrderService orderService = ServiceFactory.getInstance().createOrderService();
        try {
            List<Order> orders = orderService
                    .getByLogin(
                            (String) request.getSession().getAttribute("login")
                    );

            request.setAttribute("items", orders);
            request.getRequestDispatcher("/my_orders.jsp").forward(request, response);
            log.debug("Forward to /my_orders.jsp");

        } catch (ServiceException e) {
            log.error(e.getMessage());
            response.sendRedirect(request.getServletContext().getContextPath() + "/503error.jsp");
        }

    }
}
